package definitions;

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

    @Given("^I have no authorisation header$")
    public void iHaveNoAuthorisationHeader() {
        authTokenHelper.withoutBearerToken();
    }

}
