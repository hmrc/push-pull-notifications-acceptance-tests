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

    @When("^I have a valid bearer token for scope \"(.*)\" for my CMB application using client credentials$")
    public void iHaveAValidBearerTokenForScopeForMyCmbApplicationUsingClientCredentials(String scope) {
        clientCredentialsOauthApiSteps.successfullyGenerateAccessTokenForGivenScopeForCmbAppUsingClientCredentials(scope);
        authTokenHelper.withBearerTokenOf(clientCredentialsOauthApiSteps.bearerToken());
    }

    @When("^I have a valid bearer token for scope \"(.*)\" for my no boxes application using client credentials$")
    public void iHaveAValidBearerTokenForScopeForMyNoBoxesApplicationUsingClientCredentials(String scope) {
        clientCredentialsOauthApiSteps.successfullyGenerateAccessTokenForGivenScopeForNoBoxesAppUsingClientCredentials(scope);
        authTokenHelper.withBearerTokenOf(clientCredentialsOauthApiSteps.bearerToken());
    }

    @When("^I have an expired client credentials bearer token for scope \"(.*)\" for my standard application$")
    public void iHaveAnExpiredClientCredentialsBearerTokenForScopeForMyStandardApplication(String scope) {
        clientCredentialsOauthApiSteps.successfullyGenerateAccessTokenForGivenScopeForStandardAppUsingClientCredentials(scope);
        authTokenHelper.withBearerTokenOf("8e180a46591543e755df07ce4bfff2e9");
    }
}
