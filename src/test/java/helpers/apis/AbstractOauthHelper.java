package helpers.apis;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.annotations.Step;
import org.apache.commons.lang3.Validate;
import utilities.configuration.Configuration;
import utilities.configuration.HasConfiguration;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public abstract class AbstractOauthHelper extends HasConfiguration {

    protected ValidatableResponse lastOauthResponse;
    private String accessToken;

    protected final void callOauthTokenEndpoint(RequestSpecification spec) {
        String oauthTokenUrl = String.format("%s/oauth/token", baseApiUrl());
        lastOauthResponse = spec
                .when()
                .post(oauthTokenUrl)
                .then();
    }

    protected final void extractToken(ValidatableResponse response) {
        accessToken = response.extract().path("access_token").toString();
    }

    protected final RequestSpecification oauthRequestSpecification(String grantType, HashMap<String, String> params) {
        RequestSpecification base = given()
                .contentType(ContentType.URLENC)
                .accept("application/vnd.hmrc.1.0+json")
                .formParam("grant_type", grantType);

        if (config.isRunningOnCI()) {
            base.proxy(config.proxyPort());
        }

        // Add all parameters to the request specification
        for (String key : params.keySet()) {
            base.formParam(key, params.get(key));
        }
        return base;
    }

    final RequestSpecification oauthRequestSpecificationWithJsonPayload(String accessCode, Configuration config) {
        final String payload = String.format("{" +
                "  \"grant_type\": \"authorization_code\"," +
                "  \"code\": \"%s\"," +
                "  \"client_id\": \"%s\"," +
                "  \"client_secret\": \"%s\"," +
                "  \"redirect_uri\": \"%s\"" +
                "}", accessCode, config.clientId(), config.clientSecret(), config.authRedirectUri());
        final RequestSpecification requestSpecification = given()
                .contentType(JSON)
                .accept("application/vnd.hmrc.1.0+json")
                .body(payload);

        if (config.isRunningOnCI()) {
            requestSpecification.proxy(config.proxyPort());
        }

        return requestSpecification;
    }

    @Step
    public void assertLastOauthCallSucceeded() {
        lastOauthResponse
                .statusCode(200)
                .contentType(JSON)
                .body("access_token", not(is("")));
    }

    protected void assertLastOauthCallFailed(int statusCode, String errorCode, String errorDescription) {
        lastOauthResponse
                .statusCode(statusCode)
                .contentType(JSON)
                .body("error", is(errorCode))
                .body("error_description", is(errorDescription));
    }

    @Step
    public String bearerToken() {
        return Validate.notNull(accessToken, "Bearer Token must be generated first");
    }

    @Step
    public void assertLastOauthCallFailedDueToUnsupportedGrantType() {
        assertLastOauthCallFailed(400, "invalid_request", "unsupported grant_type");
    }

    @Step
    public void assertLastOauthCallFailedDueToInvalidScope() {
        lastOauthResponse
                .statusCode(400)
                .contentType(JSON)
                .body("error", is("invalid_scope"))
                .body("error_description", is("application does not have scopes"));
    }
}
