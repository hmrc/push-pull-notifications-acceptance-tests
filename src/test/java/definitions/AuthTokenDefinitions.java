package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import steps.apis.AuthTokenHelper;

public class AuthTokenDefinitions {

    @Steps(shared = true)
    private AuthTokenHelper authTokenHelper;

    @Given("^I have a valid server token$")
    public void iHaveAValidServerToken() {
        authTokenHelper.withValidServerToken();
    }

    @Given("^I have a valid server token for a blocked application$")
    public void iHaveAValidServerTokenBlockedApplication() {
        authTokenHelper.withValidServerTokenBlockedApplication();
    }

    @Given("^I have a invalid server token$")
    public void iHaveAInvalidServerToken() {
        authTokenHelper.withInvalidServerToken();
    }

    @Given("^I have no server token$")
    public void iHaveNoServerToken() {
        authTokenHelper.withoutAnyServerToken();
    }

    @Given("^I have no authorisation header$")
    public void iHaveNoAuthorisationHeader() {
        authTokenHelper.withoutBearerToken();
    }

    @Given("^I have an invalid bearer token$")
    public void iHaveAnInvalidBearerToken() {
        authTokenHelper.withInvalidBearerToken();
    }

}
