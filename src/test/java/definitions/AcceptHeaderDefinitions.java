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

    @Given("^I have a valid accept header for version \"(.*)\"$")
    public void iHaveAValidAcceptHeaderForVersion(String version) {
        acceptHeaderHelper.withHmrcJsonAcceptHeader(version);
    }

    @Given("^I have a valid JSON accept header$")
    public void iHaveAValidJsonAcceptHeader() {
        acceptHeaderHelper.withHmrcJsonAcceptHeader();
    }

    @Given("^I have a valid JSON accept header for version \"(.*)\"")
    public void iHaveAValidJsonAcceptHeaderForVersion(String version) {
        acceptHeaderHelper.withHmrcJsonAcceptHeader(version);
    }

    @Given("^I have an incorrect accept header version$")
    public void iHaveAnIncorrectAcceptHeaderVersion() {
        acceptHeaderHelper.withIncorrectAcceptHeaderVersion();
    }

    @Given("^I have a valid XML accept header$")
    public void iHaveAValidXmlAcceptHeader() {
        acceptHeaderHelper.withHmrcXmlAcceptHeader();
    }

    @Given("^I have a valid XML accept header for version \"(.*)\"")
    public void iHaveAValidXmlAcceptHeaderForVersion(String version) {
        acceptHeaderHelper.withHmrcXmlAcceptHeader(version);
    }

    @Given("^I have no accept header$")
    public void iHaveNoAcceptHeader() {
        acceptHeaderHelper.withNoAcceptHeader();
    }

    @Given("^I have no XML content type header$")
    public void iHaveNoXmlContentTypeHeader() {
        testApiSteps.iHaveAXmlPayloadWithNoContentTypeHeader("");
    }

    @Given("^I have an invalid accept header$")
    public void iHaveAnInvalidAcceptHeader() {
        acceptHeaderHelper.withInvalidAcceptHeader();
    }

    @Given("^I have a valid accept header with proxy$")
    public void iHaveAValidAcceptHeaderWithProxy() {
        acceptHeaderHelper.withHmrcJsonAcceptHeaderWithProxy();
    }
}
