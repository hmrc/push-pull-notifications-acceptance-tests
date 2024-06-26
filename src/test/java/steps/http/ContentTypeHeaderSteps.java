package steps.http;

import helpers.apis.ContentTypeHeaderHelper;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;

public class ContentTypeHeaderSteps {

    @Steps(shared = true)
    private ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Given("^I have a valid JSON content type header$")
    public void iHaveAValidJsonContentTypeHeader() {
        contentTypeHeaderHelper.withJsonContentTypeHeader();
    }

    @Given("^I have a valid XML content type header$")
    public void iHaveAValidXmlContentTypeHeader() {
        contentTypeHeaderHelper.withXmlContentTypeHeader();
    }

    @Given("^I have no content type header$")
    public void iHaveNoJsonContentTypeHeader() {
        contentTypeHeaderHelper.withNoContentTypeHeader();
    }

    @Given("^I have an invalid content type header$")
    public void iHaveAnInvalidJsonContentTypeHeader() {
        contentTypeHeaderHelper.withInvalidContentTypeHeader();
    }
}
