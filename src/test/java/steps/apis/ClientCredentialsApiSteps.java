package steps.apis;

import helpers.apis.AuthTokenHelper;
import helpers.apis.ClientCredentialsHelper;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class ClientCredentialsApiSteps extends ResponseSteps {

    @Steps(shared = true)
    ClientCredentialsHelper clientCredentialsOauthApiSteps;

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
