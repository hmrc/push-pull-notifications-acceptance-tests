package definitions;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import steps.helpers.AuthTokenHelper;
import steps.oauth.OauthApiSteps;

public class AuthLoginStubDefinitions extends CommonDefinitions {

    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @Steps(shared = true)
    protected OauthApiSteps oauthApiSteps;

    @Given("^I have a valid bearer token for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenForScope(String scope) {
        oauthApiSteps.generateAccessTokenUsingAccessCode(oauthApiSteps.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }
}
