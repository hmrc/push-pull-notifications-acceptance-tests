package definitions;

import configuration.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.helpers.AcceptHeaderHelper;
import steps.oauth.OauthApiSteps;
import steps.apis.PushPullNotificationsApiSteps;
import steps.helpers.ContentTypeHeaderHelper;

import static java.lang.String.format;

public class PushPullNotificationsApiDefinitions extends CommonDefinitions {

    protected Configuration config = new Configuration();

    private String correctCallBackUrl = "https://api.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications";

    @Steps(shared = true)
    private OauthApiSteps oauthApiSteps;

    @Steps
    private PushPullNotificationsApiSteps pushPullNotificationsApiSteps;

    @Steps
    ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Given("^I have a valid user agent header$")
    public void iHaveAValidUserAgentHeader() {
        pushPullNotificationsApiSteps.withUserAgent("api-subscription-fields");
    }

    @Given("^I have no user agent header$")
    public void iHaveNoUserAgentHeader() {
        pushPullNotificationsApiSteps.withUserAgent(null);
    }

    @Given("^I have an invalid user agent header$")
    public void iHaveAnInvalidUserAgentHeader() {
        pushPullNotificationsApiSteps.withUserAgent("invalid-user-agent");
    }

    @Given("^I have a valid authorization key for the PPNS API$")
    public void iHaveAValidAuthorizationKeyForThePpnsApi() {
        pushPullNotificationsApiSteps.withAuthorizationKey("ioPe1z6PwXQp4IKwtXkCQWbsPlauGCk9C0sUcFPMIWBhMEaxzs");
    }

    @Given("^I have an invalid authorization key for the PPNS API$")
    public void iHaveAnInvalidAuthorizationKeyForThePpnsApi() {
        pushPullNotificationsApiSteps.withAuthorizationKey("invalidioPe1z6PwXQp4IKwtXkCQWbsPlauGCk9C0sUcFPMIWBhMEaxzs");
    }
    @Given("^I have all valid request headers for PPNS$")
    public void iHaveAllValidRequestHeadersForPpns() {
        pushPullNotificationsApiSteps.withUserAgent("api-subscription-fields");
        contentTypeHeaderHelper.withJsonContentTypeHeader();
    }

    @Given("^I have an internal bearer token for PPNS$")
    public void iHaveAnInternalBearerTokenForPpns() {
        pushPullNotificationsApiSteps.withInternalBearerToken("Bearer 8KNIXbr3VVsSEP0CJKYPynWeMRG5IrHXNHppsY/3OKA+4PJP9PbkQ6kgTBrom4tdBD02kYrbhwqvg7/mefvhEXmgvPev3/0IgCltgX7qcblHn6mNR/qo5D+3h3nd5qmqbsb358N7s6FANUqAU7OFaDROyzjO1o+vQQUn3sDJdJL/f7Ox2YRht7vJBTxJtTKf");
    }

    @Given("^I have valid query parameters$")
    public void iHaveValidQueryParameters() {
        pushPullNotificationsApiSteps.withValidQueryParameters("myClientIs6", "someBoxName6");
    }

    @When("^I make a request to the Get Box endpoint with no query parameters$")
    public void iMakeRequestToTheGetBoxEndpointWithNoQueryParameters() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithNoQueryParameters();
    }

    @When("^I make a request to the Get Box endpoint with Client ID \"([^\"]*)\" and Box Name \"([^\"]*)\"$")
    public void iMakeRequestToGetBoxEndpoint(String clientId, String boxName) {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("clientId", "boxName", clientId, boxName);
    }

    @When("^I make a request to the Get Box endpoint with Client ID \"([^\"]*)\" and new box name$")
    public void iMakeRequestToGetBoxEndpoint(String clientId) {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("clientId", clientId, "boxName", pushPullNotificationsApiSteps.getNewBoxName());
    }

    @When("^I make a request to the Get Box endpoint with an invalid clientId query parameter name$")
    public void iMakeRequestToTheGetBoxEndpointWithAnInvalidClientIdQueryParameterName() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("invalidClientId", config.clientId(),
                "boxName", "API Platform Acceptance Test Box");
    }

    @When("^I make a request to the Get Box endpoint with an invalid boxName query parameter name$")
    public void iMakeRequestToTheGetBoxEndpointWithAnInvalidStatusQueryParameterName() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("clientId", config.clientId(),
                "invalidBoxName", "API Platform Acceptance Test Box");
    }

    @When("^I make a request to the Get Box endpoint with no client id parameter value$")
    public void iMakeRequestToTheGetBoxEndpointWithNoQueryParameterNames() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("clientId", "",
                "boxName", "API Platform Acceptance Test Box");
    }

    @When("^I make a request to the Get Box endpoint with no box name parameter value$")
    public void iMakeRequestToTheGetBoxEndpointWithNoBoxNameParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithQueryParameters("clientId", config.clientId(),
                "boxName", "");
    }

    @When("^I make a request to the create Box endpoint with Client ID \"([^\"]*)\" and Box Name \"([^\"]*)\"$")
    public void iMakeRequestToTheCreateBoxEndpoint(String clientId, String boxName) {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithPayload(clientId, boxName);
    }

    @When("^I make a new request to the create Box endpoint with Client ID \"([^\"]*)\" and a new box name$")
    public void iMakeRequestToTheCreateBoxEndpoint(String clientId) {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithPayload(clientId, pushPullNotificationsApiSteps.getNewBoxName());
    }

    @When("^I create a new box$")
    public void iCreateANewBox() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithPayload(config.clientId(), pushPullNotificationsApiSteps.getNewBoxName());
        aNewBoxIsSuccessfullyGenerated();
        iMakeRequestToGetBoxEndpoint(config.clientId());
        theNewBoxIsSuccessfullyReturned();
    }

    @When("^I make a request to the create box endpoint with invalid field names$")
    public void iMakeRequestToTheCreateBoxEndpointWithAnInvalidFieldNames() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithInvalidBody(config.clientId(), "API Platform Acceptance Test Box");
    }

    @When("^I make a request to the create box endpoint with no field names and values$")
    public void iMakeRequestToTheCreateBoxEndpointWithNoFieldNamesAndValues() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithInvalidBody("", "");
    }

    @When("^I make a request to the create box endpoint with no request body")
    public void iMakeRequestToTheCreateBoxEndpointWithNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallToCreateBoxWithNoBody();
    }

    @When("^I make a request to the callback endpoint using the new box$")
    public void iMakeARequestToTheCallbackEndpointUsingTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload(pushPullNotificationsApiSteps.getNewBoxId(), "{\"clientId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the callback endpoint where no box exists$")
    public void iMakeARequestToTheCallbackEndpointWhereNoBoxExists() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWhereNoBoxExists("{\"clientId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the create notification endpoint with a valid JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with a valid JSON payload for the new box$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidJsonPayloadForTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload(pushPullNotificationsApiSteps.getNewBoxId(), "{\"message\" : \"jsonbody\"}");
    }

    @When("^I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box$")
    public void iMakeRequestToTheCreateNotificationEndpointForAnUnsubscribedBox() {
        iCreateANewBox();
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload(pushPullNotificationsApiSteps.getNewBoxId(), "{\"message\" : \"jsonbody\"}");
        aNotificationsIsSuccessfullyGenerated();
    }

    @When("^I make a request to the validate callback endpoint with a correct URL$")
    public void iMakeRequestToTheValidateCallBackEndpointWithACorrectUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"clientId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback endpoint with an incorrect URL$")
    public void iMakeRequestToTheValidateCallBackEndpointWithAnIncorrectUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"clientId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/something\"}");
    }

    @When("^I make a request to the validate callback endpoint for a box that does not exist$")
    public void iMakeRequestToTheValidateCallBackEndpointForABoxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e4", "{\"clientId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback endpoint with a different client ID$")
    public void iMakeRequestToTheValidateCallBackEndpointWithADifferentClientId() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"clientId\" : \"DxSao_5J8fsj3bsgfyr7aWj9UcQa\", \"callbackUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/something\"}");
    }

    @When("^I make a request to the validate callback endpoint with invalid field names$")
    public void iMakeRequestToTheValidateCallBackEndpointWithInvalidFieldNames() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"invalidId\" : \"3ZdSQUrCrLEoyXFRjCgmj60qlfAa\", \"invalidUrl\" : \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}");
    }

    @When("^I make a request to the validate callback endpoint with no field values$")
    public void iMakeRequestToTheValidateCallBackEndpointWithNoFieldValues() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"clientId\" : \"\", \"callbackUrl\" : \"\"}");
    }

    @When("^I make a request to the secrets endpoint$")
    public void iMakeRequestToTheSecretsEndpoint() {
        pushPullNotificationsApiSteps.iMakeACallToSecrets(config.clientId());
    }

    @When("^I make a request to the external create client managed box endpoint with a new box name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithANewBoxName() {
        //pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox(pushPullNotificationsApiSteps.getNewBoxName());
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox(format("{\"boxName\": \"%s\"}", pushPullNotificationsApiSteps.getNewBoxName()));
    }

    @When("^I make a request to the external create client managed box endpoint with an existing box name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithAnExistingBoxName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox("{\"boxName\": \"My First Client Managed Box\"}");
    }

    @When("^I make a request to the external create client managed box endpoint with an invalid box name field name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithAnInvalidBoxNameFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox(format("{\"invalid\": \"newBoxNameTest1\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no box name field name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoBoxNameFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox(format("{\"\": \"newBoxNameTest1\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no box name field value")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoBoxNameFieldValue() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox(format("{\"boxName\": \"\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no request body")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBox("");
    }

    @When("^I make a request to the validate client managed box endpoint for box ID \"([^\"]*)\"$")
    public void iMakeRequestToTheValidateManageBoxEndpointForBoxId(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"%s\",\"clientId\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}", boxId));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid box ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidBoxIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"invalid\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"invalid\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with no box ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoBoxIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"invalid\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid box ID value$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidBoxIdValue() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"invalid\",\"clientId\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with no box ID value")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoBoxIdValue() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"\",\"clientId\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid client ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidClientIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"invalid\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with no client ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoClientIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"\":\"9D3NPzbVh7yL09zUzTYO56c7Ciwt\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with no client ID value")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoClientIdValue() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"clientId\":\"\"}"));
    }

    @When("^I make a request to the validate client managed box endpoint with no request body")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoRequestBody() {
        //pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBoxWithNoBody();
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox("");
    }

    @When("^I make a request to the validate callback endpoint with no request body")
    public void iMakeRequestToTheValidateCallBackEndpointWithNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallTCallbackWithNoPayload("5fc1f8e5-8881-4863-8a8c-5c897bb5681");
    }

    @When("^I make a request to the create notification endpoint with a valid XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "<foo>bar</foo>");
    }

    @When("^I make a request to the create notification endpoint with no XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithNoXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "");
    }

    @When("^I make a request to the create notification endpoint with an invalid XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAInvalidXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "<foo>bar<");
    }

    @When("^I make a request to the create notification endpoint with an valid UUID box that does not exist$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidUuidboxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWhereNoBoxExists("{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with an invalid UUID box that does not exist$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAInvalidUuidboxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWhereNoBoxExistsWithInValidUuid("{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with an invalid JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAnInvalidJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "{\"message\": \"json");
    }

    @When("^I make a request to the create notification endpoint with no JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithNoJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "");
    }

    @Then("^I get a successful response with acknowledged notifications")
    public void iGetASuccessfulResponseWithAcknowledgedNotifications() {
        responseSteps.expectedHttpStatusCode(204);
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(pushPullNotificationsApiSteps.getNewBoxId(), "status", "ACKNOWLEDGED");
        pushPullNotificationsApiSteps.hasAcknowledgedStatusNotifications();
    }

    @When("^I make a request to the external get box notifications endpoint with unknown query parameters$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithUnknownQuerryParameters() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "unknown", "PENDING", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid status query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidStatusQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "status", "foobar", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid fromDate query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidDateToQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "status", "PENDING", "fromDate", "2020-06-16T17:13:00.000+123", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid toDate query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidToDateQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "status", "PENDING", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000+123");
    }

    @When("^I make a request to the external get box notifications endpoint for pending status notifications$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForPendingStatusNotifications() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(pushPullNotificationsApiSteps.getNewBoxId(), "status", "PENDING");
    }

    @When("^I have a notification in status pending for a new box$")
    public void iHaveANotificationInStatusPendingForANewBox() {
        iMakeRequestToTheCreateNotificationEndpointForAnUnsubscribedBox();
        iMakeARequestToTheExternalGetBoxNotificationsEndpointForPendingStatusNotifications();
        pushPullNotificationsApiSteps.hasPendingStatusNotifications();
    }

    @When("^I make a request to the external put acknowledge notifications endpoint$")
    public void iMakeARequestToTheExternalPutAcknowledgeNotificationsEndpoint() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalPutAcknowledgeNotifications("177dcc2d-fb79-4ef8-b450-d442532b4a04");
    }

    @When("^I make a request to the external put acknowledge notifications endpoint for the new box$")
    public void iMakeARequestToTheExternalPutAcknowledgeNotificationsEndpointForTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalPutAcknowledgeNotifications(pushPullNotificationsApiSteps.getNewBoxId());
    }

    @When("^I make a request to the external put acknowledge notifications endpoint for a box that does not exist$")
    public void iMakeARequestToTheExternalPutAcknowledgeNotificationsEndpointForABoxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalPutAcknowledgeNotifications("177dcc2d-fb79-4ef8-b450-d442532b4a99");
    }

    @When("^I make a request to the external get box notifications endpoint with all valid query parameter values$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAllValidQueryParameterValues() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("5fc1f8e5-8881-4863-8a8c-5c897bb56815", "status", "PENDING", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint for the new box$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotifications(pushPullNotificationsApiSteps.getNewBoxId());
    }

    @Then("^I get a forbidden response due to an invalid or missing authorization key$")
    public void iGetAForbiddenResponseDueToAnInvalidorMissingAuthorizationKey() {
        iGetAForbiddenResponseDueToAuthorizationFailed();
    }

    @Then("^I get a forbidden response due to an invalid agent header$")
    public void iGetAForbiddenResponseDueToAnInvalidAgentHeader() {
        iGetAForbiddenResponseDueToAuthorizationFailed();
    }

    @Then("^I get a forbidden response due to missing agent header$")
    public void iGetAForbiddenResponseDueToMissingAgentHeader() {
        iGetAForbiddenResponseDueToAuthorizationFailed();
    }

    @Then("^I get a successful response with a successful true response message$")
    public void iGetASuccessfulResponseWithASuccessfulTrueResponseMessage() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("{\"successful\":true}");
    }

    @Then("^I get a successful response with the correct secret returned$")
    public void iGetASuccessfulResponseWithTheCorrectSecretReturned() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("[{\"value\":\"65ec817b58dd70bb24db31116f9017ec74704917\"}]");
    }

    @Then("^I get an invalid callback URL response$")
    public void iGetAValidationFailedResponse() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("{\"successful\":false,\"errorMessage\":\"Invalid callback URL. Check the information you have provided is correct.\"}");
    }

    @Then("^I get a successful response with the correct notification details for the new box$")
    public void iGetASuccessfulResponseWithTheCorrectClientDetailsForTheNewBox() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForTheNewBox();
    }

    @Then("^I get a successful response with the correct notification details$")
    public void iGetASuccessfulResponseWithTheCorrectClientDetails() {
        responseSteps.expectedHttpStatusCode(200);
    }

    @Then("^I get a successful response with pending notifications")
    public void iGetASuccessfulResponseWithPendingNotifications() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasPendingStatusNotifications();
    }

    @Then("^I can set a callback url$")
    public void iCanSetACallbackUrl() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertCallbackUrlUpdated();
    }

    @Then("^I get a successful response where all boxes are returned$")
    public void iGetASuccessfulResponseWhereAllBoxesAreReturned() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertAllBoxes();
    }

    @Then("^the correct box is successfully returned$")
    public void theCorrectBoxIsSuccessfullyReturned() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertBoxExists();
    }

    @Then("^the new box is successfully returned$")
    public void theNewBoxIsSuccessfullyReturned() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNewBoxExists();
    }

    @Then("^A box is successfully generated$")
    public void aBoxIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertBoxGenerated();
    }

    @Then("^A box is successfully generated with no payload$")
    public void aBoxIsSuccessfullyGeneratedWithNoPayload() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertBoxGeneratedWithNoPayload();
    }

    @Then("^A new box is successfully generated$")
    public void aNewBoxIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNewBoxGenerated();

    }

    @Then("^A new client managed box is successfully generated$")
    public void aNewClientManagedBoxIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNewClientManagedBoxGenerated();
    }

    @Then("^the existing client managed box is successfully updated$")
    public void theExistingClientManagedBoxIsSuccessfullyUpdated() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNewClientManagedBoxGenerated();
    }

    @Then("^I get a validate true response$")
    public void iGetAValidateTrueResponse() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.asserValidateClientManagedBoxResponse(true);
    }

    @Then("^I get a validate false response$")
    public void iGetAValidateFalseResponse() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.asserValidateClientManagedBoxResponse(false);
    }

    @Then("^A notifications is successfully generated$")
    public void aNotificationsIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNotificationCreated();
    }

    @Then("^I get a bad request response due to the box ID not being a valid UUID$")
    public void iGetABadRequestResponseDueToTheBoxIdNotBeingAValidUuid() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("BAD_REQUEST");
        responseSteps.expectedJsonMessage("Box ID is not a UUID");
    }

    @Then("^I get a bad request response due to content type not supported or message syntax invalid$")
    public void iGetABadRequestResponseDueToContentTypeNotSupportedOrMessageSyntaxInvalid() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Message syntax is invalid");
    }

    @Then("^I get a bad request response due missing parameter values$")
    public void iGetABadRequestResponseDueToMissingParameterValues() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Expecting boxName and clientId in request body");
    }

    @Then("^I get a bad request response due to invalid box name query parameter$")
    public void iGetABadRequestResponseDueToInvalidBoxNameQueryParameter() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonMessage("Must specify both boxName and clientId query parameters or neither");
    }

    @Then("^I get a bad request response due to invalid client ID query parameter$")
    public void iGetABadRequestResponseDueToInvalidClientIdQueryParameter() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonMessage("Must specify both boxName and clientId query parameters or neither");
    }

    @Then("^I get an invalid request payload response due to an invalid status parameter provided$")
    public void iGetAnInvalidRequestPayloadResponseDueToAnInvalidStatusQueryParameterProvided() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Invalid Status parameter provided");
    }

    @Then("^I get an invalid request payload response due to an unparsable date value parameter provided$")
    public void iGetAnInvalidRequestPayloadResponseDueToAnUnparsableDateValueQueryParameterProvided() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Unparsable DateValue Param provided");
    }

    @Then("^I get a bad request response due to missing client ID$")
    public void iGetABadRequestResponseDueToMissingClientIdAndCallbackUrl() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("clientId is required");
    }

    @Then("^I get a bad request response due to missing box ID or client ID$")
    public void iGetABadRequestResponseDueToMissingBoxIdOrClienId() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Expecting boxId and clientId in request body");
    }

    @Then("^I get a bad request response due to missing box name$")
    public void iGetABadRequestResponseDueToAnInvalidRequestPayload() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Expecting boxName in request body");
    }

    @Then("^I get an unauthorised response due to an invalid bearer token$")
    public void iGetAnUnauthorisedResponseDueToAnInvalidBearerToken() {
        responseSteps.expectedHttpStatusCode(401);
        responseSteps.expectedJsonErrorCode("UNAUTHORISED");
        responseSteps.expectedJsonMessage("Invalid bearer token");
    }

    @Then("^I get an unauthorised response due to client ID mismatch$")
    public void iGetAnUnauthorisedResponseDueToClientIdMismatch() {
        responseSteps.expectedHttpStatusCode(401);
        responseSteps.expectedJsonErrorCode("UNAUTHORISED");
        responseSteps.expectedJsonMessage("Client Id did not match");
    }

    @Then("^I get a forbidden response due to an incorrect client ID used$")
    public void iGetAnUnauthorisedResponseDueToAnIncorrectClientIdUsed() {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode("FORBIDDEN");
        responseSteps.expectedJsonMessage("Access denied");
    }

    private void iGetAForbiddenResponseDueToAuthorizationFailed() {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode("FORBIDDEN");
        responseSteps.expectedJsonMessage("Authorisation failed");
    }

    @Then("^I get a not found response due to no matching box$")
    public void iGetANotFoundResponseDueToNoMatchingBox() {
        responseSteps.expectedHttpStatusCode(404);
        responseSteps.expectedJsonErrorCode("BOX_NOT_FOUND");
        responseSteps.expectedJsonMessage("Box not found");
    }
}