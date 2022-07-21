package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.oauth.OauthApiSteps;
import steps.apis.PushPullNotificationsGatewaySteps;
import steps.helpers.ContentTypeHeaderHelper;

public class PushPullNotificationsGatewayDefinitions extends CommonDefinitions {

    @Steps(shared = true)
    private OauthApiSteps oauthApiSteps;

    @Steps
    private PushPullNotificationsGatewaySteps pushPullNotificationsGatewaySteps;

    @Steps
    ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Given("^I have a valid authorization key for PPNS gateway$")
    public void iHaveAValidAuthorizationKeyForPPNSGateway() {
        pushPullNotificationsGatewaySteps.withAuthorizationKey("C8YNrvXg7WrolVQ5nw40JdKgZuulzkAH0aUxHa6jNo4XMYIbNO");
    }

    @Given("^I have an invalid authorization key for PPNS gateway$")
    public void iHaveAnInvalidAuthorizationKeyForPPNSGateway() {
        pushPullNotificationsGatewaySteps.withAuthorizationKey("invalidg7WrolVQ5nw40JdKgZuulzkAH0aUxHa6jNo4XMYIbNO");
        contentTypeHeaderHelper.withJsonContentTypeHeader();
    }

    @Given("^I have no authorization key for PPNS gateway$")
    public void iHaveNoAuthorizationKeyForPPNSGateway() {
        pushPullNotificationsGatewaySteps.withAuthorizationKey("");
        contentTypeHeaderHelper.withJsonContentTypeHeader();;
    }

    @Given("^I have a valid user agent header for PPNS gateway$")
    public void iHaveAValidUserAgentHeaderForPPNSGateway() {
        pushPullNotificationsGatewaySteps.withUserAgent("push-pull-notifications-api");
    }

    @Given("^I have an invalid agent header for PPNS gateway$")
    public void iHaveAnInvalidAgentHeaderForPpnsGateway() {
        pushPullNotificationsGatewaySteps.withUserAgent("invalid-user-agent");
    }

    @Given("^I have no user agent header for PPNS gateway$")
    public void iHaveNoUserAgentHeaderForPPNSGateway() {
        pushPullNotificationsGatewaySteps.withUserAgent(null);
    }

    @Given("^I have a valid content type header for PPNS gateway$")
    public void iHaveAValidContentTypeHeaderForPpnsGateway() {
        contentTypeHeaderHelper.withJsonContentTypeHeader();
    }

    @Given("^I have an invalid content type header for PPNS gateway$")
    public void iHaveAnInvalidContentTypeHeaderForPpnsGateway() {
        contentTypeHeaderHelper.withInvalidContentTypeHeader();
    }

    @Given("^I have no content type header for PPNS gateway$")
    public void iHaveNoContentTypeHeaderForPpnsGateway() {
        contentTypeHeaderHelper.withNoContentTypeHeader();
    }

    @Given("^I have all valid request headers for PPNS gateway$")
    public void iHaveAllValidRequestHeadersForPpnsGateway() {
        pushPullNotificationsGatewaySteps.withAuthorizationKey("C8YNrvXg7WrolVQ5nw40JdKgZuulzkAH0aUxHa6jNo4XMYIbNO");
        pushPullNotificationsGatewaySteps.withUserAgent("push-pull-notifications-api");
        contentTypeHeaderHelper.withJsonContentTypeHeader();
    }

    @When("^I make a request to the notify endpoint with a valid payload$")
    public void iMakeARequestToTheNotifyEndpointWithAValidPayload() {
        pushPullNotificationsGatewaySteps.iMakeACallToNotifyWithPayload("{\"destinationUrl\": \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\", \"forwardedHeaders\": [{\"key\": \"somekey\", \"value\": \"somevalue\"}], \"payload\": \"{\\\"notificationId\\\": \\\"ab1826c3-88a1-4c98-8bdf-9219ac7adabe\\\", \\\"boxId\\\": \\\"bb1826c3-88a1-4c98-8bdf-9219ac7adabe\\\", \\\"messageContentType\\\": \\\"application/json\\\", \\\"message\\\": \\\"{\\\\\\\"foo\\\\\\\": 1}\\\", \\\"status\\\": \\\"PENDING\\\", \\\"createdDateTime\\\": \\\"2020-08-06T10:43:29.334+0000\\\"}\"}");
    }

    @When("^I make a request to the notify endpoint with an invalid host$")
    public void iMakeARequestToTheNotifyEndpointWithAnInvalidHost() {
        pushPullNotificationsGatewaySteps.iMakeACallToNotifyWithPayload("{\"destinationUrl\": \"https://api.staging.tax.service.gov.uk/test/api-platform-test/destination/notifications\", \"forwardedHeaders\": [{\"key\": \"somekey\", \"value\": \"somevalue\"}], \"payload\": \"{\\\"notificationId\\\": \\\"ab1826c3-88a1-4c98-8bdf-9219ac7adabe\\\", \\\"boxId\\\": \\\"bb1826c3-88a1-4c98-8bdf-9219ac7adabe\\\", \\\"messageContentType\\\": \\\"application/json\\\", \\\"message\\\": \\\"{\\\\\\\"foo\\\\\\\": 1}\\\", \\\"status\\\": \\\"PENDING\\\", \\\"createdDateTime\\\": \\\"2020-08-06T10:43:29.334+0000\\\"}\"}");
    }

    @When("^I make a request to the notify endpoint with invalid payload field names$")
    public void iMakeARequestToTheNotifyEndpointWithInvalidPayloadFieldNames() {
        pushPullNotificationsGatewaySteps.iMakeACallToNotifyWithPayload("{\"invalid\":\"https://api.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\", \"invalidHeaders\": [{\"invalidkey\": \"header-1\", \"value\": \"header-1-value\"}, {\"key\": \"header-2\", \"value\": \"header-2-value\"}], \"payload\":\"<xml>someValue</xml>\"}");
    }

    @When("^I make a request to the notify endpoint with no payload field values$")
    public void iMakeARequestToTheNotifyEndpointWithNoPayloadFieldValues() {
        pushPullNotificationsGatewaySteps.iMakeACallToNotifyWithPayload("{\"destinationUrl\":\"\", \"forwardedHeaders\": [{\"key\": \"\", \"value\": \"\"}], \"payload\":\"\"}");
    }

    @When("^I make a request to the validate callback URL endpoint with a valid payload$")
    public void iMakeARequestToTheValidateCallbackUrlEndpointWithAValidPayload() {
        pushPullNotificationsGatewaySteps.iMakeACallToValidateCallbackUrlWithPayload("{\"callbackUrl\": \"https://api.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback URL endpoint with an invalid host$")
    public void iMakeARequestToTheValidateCallbackUrlEndpointWithAnInvalidHost() {
        pushPullNotificationsGatewaySteps.iMakeACallToValidateCallbackUrlWithPayload("{\"callbackUrl\": \"https://api.staging.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback URL endpoint with an invalid payload$")
    public void iMakeARequestToTheValidateCallbackUrlEndpointWithAnInvalidPayload() {
        pushPullNotificationsGatewaySteps.iMakeACallToValidateCallbackUrlWithPayload("{\"invalid\": \"https://api.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback URL endpoint with no payload field values$")
    public void iMakeARequestToTheValidateCallbackUrlEndpointWithNoPayloadFieldValues() {
        pushPullNotificationsGatewaySteps.iMakeACallToValidateCallbackUrlWithPayload("{\"callbackUrl\": \"\"}");
    }

    @When("^I make a request to the validate callback URL endpoint with no payload$")
    public void iMakeARequestToTheValidateCallbackUrlEndpointWithNoPayload() {
        pushPullNotificationsGatewaySteps.iMakeACallToValidateCallbackUrlWithNoPayload();
    }

    @When("^I make a request to the notify endpoint with no payload$")
    public void iMakeARequestToTheNotifyEndpointWithNoPayload() {
        pushPullNotificationsGatewaySteps.iMakeACallToNotifyWithNoPayload();
    }

    @Then("^I get an unprocessable entity response due to an invalid host$")
    public void iGetAnUnprocessableEntityResponseDueToAnInvalidHost() {
        responseSteps.expectedHttpStatusCode(422);
        responseSteps.expectedJsonErrorCode("UNPROCESSABLE_ENTITY");
        responseSteps.expectedJsonMessage("Invalid host api.staging.tax.service.gov.uk");
    }
}