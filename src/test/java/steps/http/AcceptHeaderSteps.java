package steps.http;

import helpers.apis.AcceptHeaderHelper;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import helpers.apis.ContentTypeHeaderHelper;

public class AcceptHeaderSteps {

    @Steps(shared = true)
    private AcceptHeaderHelper acceptHeaderHelper;

    @Steps(shared = true)
    private ContentTypeHeaderHelper contentTypeHeaderHelper;

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
