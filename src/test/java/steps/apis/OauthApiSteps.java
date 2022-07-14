package steps.apis;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static java.lang.String.format;

public class OauthApiSteps extends AbstractOauthApiSteps {

    private static final Pattern AUTH_ID_PATTERN = Pattern.compile("auth_id=([^&]+)");
    private static final Pattern OAUTH_CODE_PATTERN = Pattern.compile("code=([0-9a-zA-Z]+)");
    private String refreshToken;
    private String oldAccessToken;
    private String oldRefreshToken;

    private RequestSpecification commonOauthRequestSpecification(String grantType) {
        return oauthRequestSpecification(grantType, HashMap.of(
                "client_id", config.clientId(),
                "client_secret", config.clientSecret(),
                "redirect_uri", config.authRedirectUri()
        ));
    }

    private RequestSpecification blockedOauthRequestSpecification(String grantType) {
        return oauthRequestSpecification(grantType, HashMap.of(
                "client_id", config.blockedClientId(),
                "client_secret", config.blockedClientSecret(),
                "redirect_uri", config.blockedAuthRedirectUri()
        ));
    }

    private RequestSpecification commonOauthRequestSpecification(String grantType, String paramName, String paramValue) {
        return commonOauthRequestSpecification(grantType).formParam(paramName, paramValue);
    }

    private RequestSpecification blockedCommonOauthRequestSpecification(String grantType, String paramName, String paramValue) {
        return blockedOauthRequestSpecification(grantType).formParam(paramName, paramValue);
    }

    private void extractTokens(ValidatableResponse response) {
        super.extractToken(response);
        refreshToken = response.extract().path("refresh_token").toString();
    }

    @Step
    public void generateAccessTokenUsingAccessCode(String accessCode) {
        RequestSpecification spec = commonOauthRequestSpecification("authorization_code", "code", accessCode);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();

        oldAccessToken = null;
        oldRefreshToken = null;
        extractTokens(lastOauthResponse);
    }

    @Step
    public void generateAccessTokenUsingAccessCodeWithAnAuthHeader(String accessCode) {
        RequestSpecification spec = commonOauthRequestSpecification("authorization_code", "code", accessCode)
                .auth().oauth2("xyz123");
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();

        oldAccessToken = null;
        oldRefreshToken = null;
        extractTokens(lastOauthResponse);
    }




    @Step
    public void generateAccessTokenWithJsonPayloadUsingAccessCode(String accessCode) {
        RequestSpecification spec = oauthRequestSpecificationWithJsonPayload(accessCode, config);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();

        oldAccessToken = null;
        oldRefreshToken = null;
        extractTokens(lastOauthResponse);
    }



    @Step
    public void generateAccessTokenUsingAccessCodeBlockedApp(String accessCode) {
        RequestSpecification spec = blockedCommonOauthRequestSpecification("authorization_code", "code", accessCode);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();

        oldAccessToken = null;
        oldRefreshToken = null;
        extractTokens(lastOauthResponse);
    }

    private void renewAccessTokenUsingRefreshToken(String refreshToken) {
        refreshTokenSleep();
        RequestSpecification spec = commonOauthRequestSpecification("refresh_token", "refresh_token", refreshToken);
        callOauthTokenEndpoint(spec);
    }

    private void refreshTokenSleep() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void attemptToRenewAccessTokenUsingUsedRefreshToken() {
        renewAccessTokenUsingRefreshToken(oldRefreshToken);
        assertLastOauthCallFailed(400, "invalid_grant", "refresh_token is invalid");
    }

    @Step
    public String oldAccessToken() {
        return Validate.notNull(oldAccessToken, "Old Bearer Token not generated - need to refresh first");
    }

    @Step
    public void assertLastOauthCallFailedDueToInvalidRefreshToken() {
        assertLastOauthCallFailed(400, "invalid_grant", "refresh_token is invalid");
    }

    @Step
    public String generateAccessCode(final String scope) {
        final Response signInResponse = signIn(config.clientId(), config.authRedirectUri(), scope, false);
        final String authId = startAuthJourney(signInResponse.getHeader("Location"));
        final Response grantAuthorityResponse = grantAuthority(signInResponse.cookie("mdtp"), authId);
        final String csrfToken = grantAuthorityResponse.getBody().htmlPath().getString("html.'**'.find {input -> input.@name == 'csrfToken'}.@value");
        return getAccessCode(grantAuthorityResponse.getCookie("mdtp"), authId, csrfToken);
    }

    @Step
    public void generateAccessCodeForAnInvalidClientId() {
        lastOauthResponse = signIn("InvalidClientID", config.authRedirectUri(), "hello", true).then();
        assertLastOauthCallFailed(400, "invalid_request", "client_id is invalid");
    }

    @Step
    public void generateAccessCodeForAnInvalidRedirectUri() {
        lastOauthResponse = signIn(config.clientId(), "invalidRedirectUri", "hello", true).then();
        assertLastOauthCallFailed(400, "invalid_request", "redirect_uri is invalid");
    }

    @Step
    public void generateAccessCodeForAnInvalidScope() {
        lastOauthResponse = signIn(config.clientId(), config.authRedirectUri(), "invalidScope", true).then();
        assertLastOauthCallFailed(400, "invalid_scope", "scope is invalid");
    }

    @Step
    public void generateAccessCodeForAPrivilegedApplication() {
        lastOauthResponse = signIn(config.privilegedClientId(), config.authRedirectUri(), "invalidScope", true).then();
        assertLastOauthCallFailed(400, "invalid_request", "application type is invalid");
    }

    @Step
    public void generateAccessCodeForAnRopcApplication() {
        lastOauthResponse = signIn(config.ropcClientId(), config.authRedirectUri(), "invalidScope", true).then();
        assertLastOauthCallFailed(400, "invalid_request", "application type is invalid");
    }

    @Step
    public void generateAccessCodeWithAClientSecret() {
        lastOauthResponse = signIn(config.clientId(), config.authRedirectUri(), "invalidScope", true, config.clientSecret()).then();
        assertLastOauthCallFailed(400, "invalid_request", "client_secret should NOT be present");
    }

    private Response signIn(final String clientId, final String redirectUri, final String scope, final boolean followRedirects) {
        return signIn(clientId, redirectUri, scope, followRedirects, null);
    }

    private Response signIn(final String clientId, final String redirectUri, final String scope, final boolean followRedirects, final String clientSecret) {
        String redirectionUrl;
        if (clientSecret != null) {
            redirectionUrl = format("/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code&client_secret=%s",
                    clientId, redirectUri, scope, clientSecret);
        } else {
            redirectionUrl = format("/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", clientId, redirectUri, scope);
        }

        final RequestSpecification spec = given()
                .redirects().follow(followRedirects)
                .contentType(URLENC)
                .formParam("authorityId", "0000001750989867")
                .formParam("redirectionUrl", redirectionUrl)
                .formParam("credentialStrength", "strong")
                .formParam("confidenceLevel", "250")
                .formParam("affinityGroup", "Individual")
                .formParam("email", "api.platform@digital.hmrc.gov.uk")
                .formParam("credentialRole", "User")
                .formParam("nino", "CK562300D")
                .formParam("enrolment[0].name", "IR-SA")
                .formParam("enrolment[0].taxIdentifier[0].name", "UTR")
                .formParam("enrolment[0].taxIdentifier[0].value", "2760992196")
                .formParam("enrolment[0].state", "Activated");

        return withProxy(spec).post(config.baseUrl() + "/auth-login-stub/gg-sign-in");
    }

    @Step
    public String generateAccessCodeBlockedApp(final String scope) {
        final Response signInResponse = signIn(config.blockedClientId(), config.blockedAuthRedirectUri(), scope, false);
        final String authId = startAuthJourney(signInResponse.getHeader("Location"));
        final Response grantAuthorityResponse = grantAuthority(signInResponse.cookie("mdtp"), authId);
        final String csrfToken = grantAuthorityResponse.getBody().htmlPath().getString("html.'**'.find {input -> input.@name == 'csrfToken'}.@value");
        return getAccessCode(grantAuthorityResponse.getCookie("mdtp"), authId, csrfToken);
    }

    private String startAuthJourney(final String location) {
        final RequestSpecification spec = given().redirects().follow(false);
        final Response response = withProxy(spec).get(config.baseUrl() + location);
        return getMatch(AUTH_ID_PATTERN.matcher(response.getHeader("Location")));
    }

    private Response grantAuthority(final String mdtpCookie, final String authId) {
        final RequestSpecification spec = given()
                .redirects().follow(true)
                .cookie("mdtp", mdtpCookie);

        return withProxy(spec).get(format("%s/oauth/grantscope?auth_id=%s", config.baseUrl(), authId));
    }

    private String getAccessCode(final String mdtpCookie, final String authId, final String csrfToken) {
        final RequestSpecification spec = given()
                .redirects().follow(false)
                .contentType(URLENC)
                .header("Csrf-Token", csrfToken)
                .cookie("mdtp", mdtpCookie)
                .formParam("auth_id", authId);

        final Response response = withProxy(spec).post(config.baseUrl() + "/oauth/grantscope");

        return getMatch(OAUTH_CODE_PATTERN.matcher(response.getHeader("Location")));
    }

    private RequestSpecification withProxy(final RequestSpecification spec) {
        if (config.isRunningOnCI()) {
            spec.proxy(config.proxyPort());
        }
        return spec;
    }

    private String getMatch(final Matcher matcher) {
        if (!matcher.find()) {
            throw new RuntimeException("No matches found for pattern " + matcher.pattern());
        }
        return matcher.group(1);
    }
}
