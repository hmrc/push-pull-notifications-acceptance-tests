package steps.oauth;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.oauth.OAuthErrorPage;
import pages.oauth.OAuthSignInPage;
import pages.oauth.OutOfBandAuthCodePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class OAuthSignInSteps {
    OAuthSignInPage signInPage;
    OutOfBandAuthCodePage outOfBandAuthCodePage;
    OAuthErrorPage errorPage;

    @When("^the API Platform IV and Grant Page Suppression application sends the end user to the oauth sign in page for scopes \"([^\"]*)\"$")
    public void theIvAndGrantSuppressionAppSendsTheUserToOauthSignIn(String scopes) throws Throwable {
        signInPage.goToSignInPage("TPAegYLXrCEEI6y9wfMySCayjowa", scopes);
    }

    @Then("^the OAuth sign in page is shown")
    public void theSignInPageIsShown() throws Throwable {
        assertThat(signInPage.getPageTitle(), is(signInPage.pageTitle));
    }

    @And("^the end user enters userId \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theUserEntersCredentials(String userId, String password) throws Throwable {
        signInPage.enterUserId(userId);
        signInPage.enterPassword(password);
    }

    @And("the end user clicks the sign in button")
    public void theUserClicksSignIn() throws Throwable {
        signInPage.signIn();
    }

    @Then("the end user is shown the out-of-band auth code page")
    public void theOutOfBandAuthCodePageIsShown() {
        assertThat(outOfBandAuthCodePage.getPageTitle(), is(outOfBandAuthCodePage.pageTitle));
    }

    @Then("the end user is shown an error that the app has requested invalid scopes")
    public void anErrorAboutInvalidScopesIsShown() {
        assertThat(errorPage.getErrorText(), containsString("invalid_scope"));
    }
}
