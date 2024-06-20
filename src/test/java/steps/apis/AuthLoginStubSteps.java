package steps.apis;

import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import helpers.apis.AuthTokenHelper;
import helpers.apis.OauthHelper;

public class AuthLoginStubSteps extends ResponseSteps {

    @Steps(shared = true)
    protected OauthHelper oauthHelper;
    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @Given("^I have a valid bearer token for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenForScope(String scope) {
        oauthHelper.generateAccessTokenUsingAccessCode(oauthHelper.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthHelper.bearerToken());
    }
}
