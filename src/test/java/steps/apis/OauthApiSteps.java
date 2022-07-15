package steps.apis;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;

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

    private RequestSpecification commonOauthRequestSpecification(String grantType, String paramName, String paramValue) {
        return commonOauthRequestSpecification(grantType).formParam(paramName, paramValue);
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
    public String generateAccessCode(final String scope) {
        final Response signInResponse = signIn(config.clientId(), config.authRedirectUri(), scope, false);
        final String authId = startAuthJourney(signInResponse.getHeader("Location"));
        final Response grantAuthorityResponse = grantAuthority(signInResponse.cookie("mdtp"), authId);
        final String csrfToken = grantAuthorityResponse.getBody().htmlPath().getString("html.'**'.find {input -> input.@name == 'csrfToken'}.@value");
        return getAccessCode(grantAuthorityResponse.getCookie("mdtp"), authId, csrfToken);
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
