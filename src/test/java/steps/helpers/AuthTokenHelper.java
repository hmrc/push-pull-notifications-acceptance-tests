package steps.helpers;

import io.restassured.authentication.ExplicitNoAuthScheme;
import steps.apis.ApiSteps;

import static io.restassured.RestAssured.oauth2;

public class AuthTokenHelper extends ApiSteps {

    private static final String INVALID_SERVER_TOKEN = "axyz123";

    public void withBearerTokenOf(String bearerToken) {
        builder().setAuth(oauth2(bearerToken));
    }

    public void withoutBearerToken() {
        builder().setAuth(new ExplicitNoAuthScheme());
    }

    private void withServerToken(String token) {
        builder().setAuth(oauth2(token));
    }

    public void withoutAnyServerToken() {
        builder().setAuth(new ExplicitNoAuthScheme());
    }

    public void withValidServerToken() {
        withServerToken(config.authServerToken());
    }

    public void withValidServerTokenBlockedApplication() {
        withServerToken(config.blockedAuthServerToken());
    }

    public void withInvalidServerToken() {
        withServerToken(INVALID_SERVER_TOKEN);
    }

    public void withInvalidBearerToken() {
        withBearerTokenOf(INVALID_SERVER_TOKEN);
    }
}