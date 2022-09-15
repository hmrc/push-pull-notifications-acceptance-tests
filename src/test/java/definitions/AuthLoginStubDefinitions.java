package definitions;

import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import steps.helpers.AuthTokenHelper;
import steps.oauth.OauthApiSteps;

public class AuthLoginStubDefinitions extends CommonDefinitions {

    @Steps(shared = true)
    protected OauthApiSteps oauthApiSteps;
    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @Given("^I have a valid bearer token for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenForScope(String scope) {
        oauthApiSteps.generateAccessTokenUsingAccessCode(oauthApiSteps.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }
}
