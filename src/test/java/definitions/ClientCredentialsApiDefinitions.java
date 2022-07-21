package definitions;

import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.helpers.AuthTokenHelper;
import steps.oauth.ClientCredentialsApiSteps;

public class ClientCredentialsApiDefinitions extends CommonDefinitions {

    @Steps(shared = true)
    ClientCredentialsApiSteps clientCredentialsOauthApiSteps;

    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @When("^I have a valid bearer token for scope \"(.*)\" for my standard application using client credentials$")
    public void iHaveAValidBearerTokenForScopeForMyStandardApplicationUsingClientCredentials(String scope) {
        clientCredentialsOauthApiSteps.successfullyGenerateAccessTokenForGivenScopeForStandardAppUsingClientCredentials(scope);
        authTokenHelper.withBearerTokenOf(clientCredentialsOauthApiSteps.bearerToken());
    }
}
