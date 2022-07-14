package definitions;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import authloginstub.GrantAuthorityPage;
import authloginstub.LocalhostRedirectPage;
import authloginstub.StartAuthorityPage;
import steps.apis.AuthTokenHelper;
import steps.apis.CommonResponseSteps;
import steps.apis.OauthApiSteps;
import steps.oauth.AuthLoginStubSteps;

public class AuthLoginStubDefinitions extends CommonDefinitions {

    @Steps(shared = true)
    private AuthLoginStubSteps authLoginStubSteps;

    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @Steps(shared = true)
    protected OauthApiSteps oauthApiSteps;

    @Steps(shared = true)
    private CommonResponseSteps commonResponseSteps;

    private StartAuthorityPage startAuthorityPage;
    private GrantAuthorityPage grantAuthorityPage;
    private LocalhostRedirectPage localhostRedirectPage;

    @Given("^I have a standard application$")
    public void iHaveAStandardApplication() {
    }

    @Given("^I have a valid bearer token for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenForScope(String scope) {
        oauthApiSteps.generateAccessTokenUsingAccessCode(oauthApiSteps.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @Given("^I have a valid bearer token created with an authorisation header for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenCreatedWithAnAuthorisationHeaderForScope(String scope) {
        oauthApiSteps.generateAccessTokenUsingAccessCodeWithAnAuthHeader(oauthApiSteps.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @Given("^I have a valid bearer token using a JSON payload for scope \"(.*)\"$")
    public void iHaveAValidBearerTokenUsingAJsonPayloadForScope(String scope) {
        oauthApiSteps.generateAccessTokenWithJsonPayloadUsingAccessCode(oauthApiSteps.generateAccessCode(scope));
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @Then("^I can generate an access code to create a valid bearer token$")
    public void iCanGenerateAnAccessCodeToCreateAValidBearerToken() {
        authLoginStubSteps.grantAuthorityFromIv();

        String authorizationCode = localhostRedirectPage.getUrlAccessCode();

        oauthApiSteps.generateAccessTokenUsingAccessCode(authorizationCode);

        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @When("^I use the authorisation code to get an bearer token$")
    public void iUseTheAuthorisationCodeToGetABearerToken() {
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @When("^I refresh the bearer token with the used refresh token$")
    public void iRefreshTheBearerTokenWithTheUsedRefreshToken() {
        oauthApiSteps.attemptToRenewAccessTokenUsingUsedRefreshToken();
        authTokenHelper.withBearerTokenOf(oauthApiSteps.bearerToken());
    }

    @Then("^I get an invalid grant error$")
    public void iGetAnInvalidGrantError() {
        oauthApiSteps.assertLastOauthCallFailedDueToInvalidRefreshToken();
    }

    @But("^I use the old bearer token$")
    public void iUseTheOldBearerToken() {
        authTokenHelper.withBearerTokenOf(oauthApiSteps.oldAccessToken());
    }

    @Given("^I have an invalid redirect URI$")
    public void iHaveAnInvalidRedirectUri() {
    }
}

