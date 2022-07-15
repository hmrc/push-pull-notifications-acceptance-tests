package definitions;

import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import steps.helpers.AcceptHeaderHelper;
import steps.helpers.ContentTypeHeaderHelper;
import steps.apis.TestApiSteps;

public class AcceptHeaderDefinitions {

    @Steps(shared = true)
    private AcceptHeaderHelper acceptHeaderHelper;

    @Steps(shared = true)
    private ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Steps(shared = true)
    private TestApiSteps testApiSteps;

    @Given("^I have a valid JSON accept header$")
    public void iHaveAValidJsonAcceptHeader() {
        acceptHeaderHelper.withHmrcJsonAcceptHeader();
    }

    @Given("^I have an incorrect accept header version$")
    public void iHaveAnIncorrectAcceptHeaderVersion() {
        acceptHeaderHelper.withIncorrectAcceptHeaderVersion();
    }

    @Given("^I have no accept header$")
    public void iHaveNoAcceptHeader() {
        acceptHeaderHelper.withNoAcceptHeader();
    }

    @Given("^I have an invalid accept header$")
    public void iHaveAnInvalidAcceptHeader() {
        acceptHeaderHelper.withInvalidAcceptHeader();
    }
}
