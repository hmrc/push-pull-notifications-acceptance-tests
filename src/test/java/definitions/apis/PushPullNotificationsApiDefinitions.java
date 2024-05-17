package definitions.apis;

import configuration.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.apis.PushPullNotificationsApiSteps;
import steps.helpers.ContentTypeHeaderHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

public class PushPullNotificationsApiDefinitions extends ResponseDefinitions {

    protected Configuration config = new Configuration();

    @Steps
    ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Steps
    private PushPullNotificationsApiSteps pushPullNotificationsApiSteps;

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
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload(pushPullNotificationsApiSteps.getNewBoxId(), (format("{\"clientId\" : \"%s\", \"callbackUrl\" : \"%s\"}", config.clientId(), config.callbackUrl())));
    }

    @When("^I make a request to the callback endpoint where no box exists$")
    public void iMakeARequestToTheCallbackEndpointWhereNoBoxExists() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWhereNoBoxExists(format("{\"clientId\" : \"%s\", \"callbackUrl\" : \"%s\"}", config.clientId(), config.callbackUrl()));
    }

    @When("^I make a request to the create notification endpoint with a valid JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with a valid JSON payload for the new box$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidJsonPayloadForTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload(pushPullNotificationsApiSteps.getNewBoxId(), "{\"message\" : \"jsonbody\"}");
    }

    /// NEW METHOD TO TAKE FILE

    @When("^I make a request to the create notification endpoint with a valid max size JSON payload for the new box$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidMaxSizeJsonPayloadForTheNewBox() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayloadFile(pushPullNotificationsApiSteps.getNewBoxId());
    }

    @When("^I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box$")
    public void iMakeRequestToTheCreateNotificationEndpointForAnUnsubscribedBox() {
        iCreateANewBox();
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload(pushPullNotificationsApiSteps.getNewBoxId(), "{\"message\" : \"jsonbody\"}");
        aNotificationsIsSuccessfullyGenerated();
    }

    @When("^I make a request to the create wrapped notification endpoint with a JSON notification$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAJsonNotification() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "        \"notification\": {\n" +
                "            \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "            \"contentType\": \"application/json\"\n" +
                "        },\n" +
                "        \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "        \"version\": \"1\"\n" +
                "    }");
    }

    @When("^I make a request to the create wrapped notification endpoint with a XML notification$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAXMLNotification() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "        \"notification\": {\n" +
                "            \"body\": \"<someXml>foo bar</someXml>\",\n" +
                "            \"contentType\": \"application/xml\"\n" +
                "        },\n" +
                "        \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "        \"version\": \"1\"\n" +
                "    }");
    }

    @When("^I make a request to the create wrapped notification endpoint without a confirmation URL$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithoutAConfirmationUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with optional private headers$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnOptionalPrivateHeader() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "   \"notification\":{\n" +
                "      \"body\":\"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   },\n" +
                "   \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "   \"version\":\"1\",\n" +
                "   \"privateHeaders\":[\n" +
                "      {\n" +
                "         \"name\":\"fooDuplicate\",\n" +
                "         \"value\":\"fooValueDuplicate\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"fooDuplicate\",\n" +
                "         \"value\":\"fooValueDuplicate\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"\",\n" +
                "         \"value\":\"\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo4\",\n" +
                "         \"value\":\"fooValue4\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo5\",\n" +
                "         \"value\":\"fooValue5\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an empty optional private header$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnEmptyOptionalPrivateHeader() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "   \"notification\":{\n" +
                "      \"body\":\"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   },\n" +
                "   \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "   \"version\":\"1\",\n" +
                "   \"privateHeaders\":[\n" +
                "      {\n" +
                "         \"name\":\"\",\n" +
                "         \"value\":\"\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with more than 5 optional private headers$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithMoreThanFiveOptionalPrivateHeader() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "   \"notification\":{\n" +
                "      \"body\":\"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   },\n" +
                "   \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "   \"version\":\"1\",\n" +
                "   \"privateHeaders\":[\n" +
                "      {\n" +
                "         \"name\":\"foo\",\n" +
                "         \"value\":\"fooValue\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo2\",\n" +
                "         \"value\":\"fooValue2\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo3\",\n" +
                "         \"value\":\"fooValue4\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo4\",\n" +
                "         \"value\":\"fooValue4\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo5\",\n" +
                "         \"value\":\"fooValue5\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"foo6\",\n" +
                "         \"value\":\"fooValue6\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an empty string confirmation URL$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnEmptyStringConfirmationUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"confirmationUrl\": \"example.com\",\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an invalid confirmation URL$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnInvalidConfirmationUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"confirmationUrl\": \"\",\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an invalid message version$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnInvalidMessageVersion() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"2\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with a box that does not exist$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithABoxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWhereNoBoxExists("{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an invalid UUID$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnInvalidUuid() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithInvalidUuid("{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an invalid JSON payload$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnInvalidJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "");
    }

    @When("^I make a request to the create wrapped notification endpoint with no notification field name$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithNoNotificationFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with an invalid notification content type header$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithAnInvalidNotificationContentTypeHeader() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/xml\"\n" +
                "    },\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no notification field vale$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithNoNotificationFieldValue() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": ,\n" +
                "    \"version\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no version field name$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithANoVersionFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"\": \"1\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no version field vale$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithNoVersionFieldValue() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "        \"contentType\": \"application/json\"\n" +
                "    },\n" +
                "    \"version\": \"\"\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no private header name field$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithPrivateHeaderNameField() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "   \"notification\":{\n" +
                "      \"body\":\"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   },\n" +
                "   \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "   \"version\":\"1\",\n" +
                "   \"privateHeaders\":[\n" +
                "      {\n" +
                "         \"\":\"foo\",\n" +
                "         \"value\":\"fooValue\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no private header value field$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithPrivateHeaderValueField() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\n" +
                "   \"notification\":{\n" +
                "      \"body\":\"{\\\"foo\\\":\\\"bar\\\"}\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   },\n" +
                "   \"confirmationUrl\":\"https://api-platform-test.protected.mdtp/destination/notifications\",\n" +
                "   \"version\":\"1\",\n" +
                "   \"privateHeaders\":[\n" +
                "      {\n" +
                "         \"name\":\"foo\",\n" +
                "         \"\":\"fooValue\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
    }

    @When("^I make a request to the create wrapped notification endpoint with no JSON payload$")
    public void iMakeRequestToTheCreateWrappedNotificationEndpointWithNoJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateWrappedNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "");
    }

    @When("^I make a request to the callback endpoint with a correct URL$")
    public void iMakeRequestToTheCallBackEndpointWithACorrectUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("3b8e4dd3-a029-4301-a912-1220f3196387", (format("{\"clientId\" : \"%s\", \"callbackUrl\" : \"%s\"}", config.clientId(), config.callbackUrl())));
    }

    @When("^I make a request to the callback endpoint with an incorrect URL$")
    public void iMakeRequestToTheCallBackEndpointWithAnIncorrectUrl() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("3b8e4dd3-a029-4301-a912-1220f3196387", (format("{\"clientId\" : \"%s\", \"callbackUrl\" : \"https://invalid.callbac.url\"}", config.clientId())));
    }

    @When("^I make a request to the callback endpoint for a box that does not exist$")
    public void iMakeRequestToTheCallBackEndpointForABoxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("3b8e4dd3-a029-4301-a912-1220f3196388", (format("{\"clientId\" : \"%s\", \"callbackUrl\" : \"%s\"}", config.clientId(), config.callbackUrl())));
    }

    @When("^I make a request to the callback endpoint with a different client ID$")
    public void iMakeRequestToTheCallBackEndpointWithADifferentClientId() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", (format("{\"clientId\" : \"DxSao_5J8fsj3bsgfyr7aWj9UcQa\", \"callbackUrl\" : \"%s\"}", config.callbackUrl())));
    }

    @When("^I make a request to the callback endpoint with invalid field names$")
    public void iMakeRequestToTheCallBackEndpointWithInvalidFieldNames() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", (format("{\"invalidId\" : \"%s\", \"invalidUrl\" : \"%s\"}", config.clientId(), config.callbackUrl())));
    }

    @When("^I make a request to the callback endpoint with no field values$")
    public void iMakeRequestToTheCallBackEndpointWithNoFieldValues() {
        pushPullNotificationsApiSteps.iMakeACallToCallbackWithPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "{\"clientId\" : \"\", \"callbackUrl\" : \"\"}");
    }

    @When("^I make a request to the secrets endpoint$")
    public void iMakeRequestToTheSecretsEndpoint() {
        pushPullNotificationsApiSteps.iMakeACallToSecrets(config.clientId());
    }

    @When("^I make a request to the external get a list of boxes endpoint$")
    public void iMakeRequestToTheExternalGetAListOfBoxesEndpoint() {
        pushPullNotificationsApiSteps.iMakeACallToExternalGetAListOfdBoxes();
    }

    @When("^I make a request to the external get a list of boxes endpoint with an expired client credentials bearer token$")
    public void iMakeARequestToTheExternalGetAListOfBoxesEndpointWithAnExpiredClientCredentialsBearerToken() {
        pushPullNotificationsApiSteps.iMakeACallToExternalGetAListOfBoxesWithExpiredToken();
    }

    @When("^I make a request to the external create client managed box endpoint with a new box name$")
    public void iMakeRequestToTheExternalCreateClientManagedBoxEndpointWithANewBoxName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox(format("{\"boxName\": \"%s\"}", pushPullNotificationsApiSteps.getNewBoxName()));
    }

    @When("^I make a request to the external create client managed box endpoint with an existing box name$")
    public void iMakeRequestToTheExternalCreateClientManageBoxEndpointWithAnExistingBoxName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox("{\"boxName\": \"My First Client Managed Box\"}");
    }

    @When("^I make a request to the external update client managed box endpoint with an invalid callback URL for box ID \"([^\"]*)\"$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithAnInvalidCallbackUrlForBoxId(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"callbackUrl\": \"https://example.com\"}"), boxId);
    }

    @When("^I make a request to the external update client managed box endpoint with a valid callback URL for box ID \"([^\"]*)\"$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithAValidCallbackUrlForBoxId(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"callbackUrl\": \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}"), boxId);
    }

    @When("^I make a request to the external update client managed box endpoint with no callback URL for box ID \"([^\"]*)\"$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithNoCallbackUrlForBoxId(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"callbackUrl\": \"\"}"), boxId);
    }

    @When("^I update my callback to a valid URL \"([^\"]*)\"$")
    public void iUpdateMyCallbackToAValidUrl(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"callbackUrl\": \"\"}"), boxId);
    }

    @When("^I make a request to the external create client managed box endpoint with an expired client credentials bearer token$")
    public void iMakeARequestToTheExternalCreateClientManageBoxEndpointWithAnExpiredClientCredentialsBearerToken() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManageBoxWithExpiredBearerToken(format("{\"boxName\": \"%s\"}", pushPullNotificationsApiSteps.getNewBoxName()));
    }

    @When("^I make a request to the external update client managed box endpoint with an expired client credentials bearer token$")
    public void iMakeARequestToTheExternalUpdateClientManageBoxEndpointWithAnExpiredClientCredentialsBearerToken() {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManageBoxWithExpiredBearerToken(format("{\"callbackUrl\": \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with an invalid box name field name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithAnInvalidBoxNameFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox(format("{\"invalid\": \"newBoxNameTest1\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no box name field name$")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoBoxNameFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox(format("{\"\": \"newBoxNameTest1\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no box name field value")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoBoxNameFieldValue() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox(format("{\"boxName\": \"\"}"));
    }

    @When("^I make a request to the external create client managed box endpoint with no request body")
    public void iMakeRequestToTheCreateClientManageBoxEndpointWithNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallToExternalCreateClientManagedBox("");
    }

    @When("^I make a request to the external update client managed box endpoint with an invalid callback URL field name$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithAnInvalidCallbackUrlFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"invalid\": \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}"), "2eb7c0a-4571-44ad-9cbc-8d5143c0af7f");
    }

    @When("^I make a request to the external update client managed box endpoint with no callback URL field name$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithNNoCallbackUrlFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxId(format("{\"\": \"https://api.isc.qa.tax.service.gov.uk/test/api-platform-test/destination/notifications\"}"), "2eb7c0a-4571-44ad-9cbc-8d5143c0af7f");
    }
    @When("^I make a request to the external update client managed box endpoint with no request body$")
    public void iMakeRequestToTheExternalUpdateClientManagedBoxEndpointWithNNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallToExternalUpdateClientManagedBoxWithBoxIdAndNoPayload("a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f");
    }

    @And("^I can delete the created client managed box by ID$")
    public void iCanDeleteTheCreatedClientManagedBoxById() {
        pushPullNotificationsApiSteps.iMakeACallToExternalDeleteClientManageBoxWithNewClientManagedBoxId();
        responseSteps.expectedHttpStatusCode(204);
    }

    @When("^I make a call to the delete client managed box endpoint with a box ID that does not exist$")
    public void iMakeACalltoTheDeleteClientManagedBoxEndpointWithABoxIdThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToExternalDeleteClientManageBoxWithClientManagedBoxId("ccc1a3e7-2b73-475a-a14c-1428ab3b46bc");
    }

    @When("^I make a call to the delete client managed box endpoint with a non ownership box ID")
    public void iMakeACalltoTheDeleteClientManagedBoxEndpointWithANonOwnershipBoxId() {
        pushPullNotificationsApiSteps.iMakeACallToExternalDeleteClientManageBoxWithClientManagedBoxId("5589dd9a-40e9-4dec-bbe3-9d83f5102a2a");
    }

    @When("^I make a call to the delete client managed box endpoint with a default box ID")
    public void iMakeACalltoTheDeleteClientManagedBoxEndpointWithADefaultBoxId() {
        pushPullNotificationsApiSteps.iMakeACallToExternalDeleteClientManageBoxWithClientManagedBoxId("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3");
    }

    @When("^I make a request to the external delete client managed box endpoint with an expired client credentials bearer token$")
    public void iMakeRequestToTheCreateDeleteManageBoxEndpointWithAnExpired() {
        pushPullNotificationsApiSteps.iMakeACallToExternalDeleteClientManageBoxWithExpiredBearerToken("a5e3203d-a57e-4787-ba72-2dbfc294455f");
    }

    @When("^I make a request to the validate client managed box endpoint for box ID \"([^\"]*)\"$")
    public void iMakeRequestToTheValidateManageBoxEndpointForBoxId(String boxId) {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"%s\",\"clientId\":\"%s\"}", boxId, config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid box ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidBoxIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"invalid\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"clientId\":\"%s\"}", config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with no box ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoBoxIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"clientId\":\"%s\"}", config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid box ID value$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidBoxIdValue() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"invalid\",\"clientId\":\"%s\"}", config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with no box ID value")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoBoxIdValue() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"\",\"clientId\":\"%s\"}", config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with an invalid client ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithAnInvalidClientIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"invalid\":\"%s\"}", config.cmbClientId()));
    }

    @When("^I make a request to the validate client managed box endpoint with no client ID field name$")
    public void iMakeRequestToTheValidateClientManagedBoxEndPointWithNoClientIdFieldName() {
        pushPullNotificationsApiSteps.iMakeACallToValidatedClientManageBox(format("{\"boxId\": \"a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f\",\"\":\"%s\"}", config.cmbClientId()));
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

    @When("^I make a request to the callback endpoint with no request body")
    public void iMakeRequestToTheCallBackEndpointWithNoRequestBody() {
        pushPullNotificationsApiSteps.iMakeACallTCallbackWithNoPayload("5fc1f8e5-8881-4863-8a8c-5c897bb5681");
    }

    @When("^I make a request to the create notification endpoint with a valid XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("046ceee5-e43f-4159-b5ce-8df5f2b9d1e3", "<foo>bar</foo>");
    }

    @When("^I make a request to the create notification endpoint with no XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithNoXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "");
    }

    @When("^I make a request to the create notification endpoint with an invalid XML payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAInvalidXmlPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithXmlPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "<foo>bar<");
    }

    @When("^I make a request to the create notification endpoint with an valid UUID box that does not exist$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAValidUuidboxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWhereNoBoxExists("{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with an invalid UUID$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAInvalidUuidBoxThatDoesNotExist() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithInvalidUuid("{\"message\": \"jsonbody\"}");
    }

    @When("^I make a request to the create notification endpoint with a message exceeding the max size$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAMessageExceedingTheMaxSize() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayloadFileTooLarge("3b8e4dd3-a029-4301-a912-1220f3196387");
    }

    @When("^I make a request to the create notification endpoint with an invalid JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithAnInvalidJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "{\"message\": \"json");
    }

    @When("^I make a request to the create notification endpoint with no JSON payload$")
    public void iMakeRequestToTheCreateNotificationEndpointWithNoJsonPayload() {
        pushPullNotificationsApiSteps.iMakeACallToCreateNotificationsWithJsonPayload("3b8e4dd3-a029-4301-a912-1220f3196387", "");
    }

    @Given("^I have a generated notification in an acknowledged status$")
    public void iHaveAGeneratedNotificationInAnAckowledgedStatus() {
        iHaveANotificationInStatusPendingForANewBox();
        contentTypeHeaderHelper.withJsonContentTypeHeader();
        iMakeARequestToTheExternalPutAcknowledgeNotificationsEndpointForTheNewBox();
        iGetASuccessfulResponseWithNotificationsNowAcknowledged();
    }

    @When("^I make a request to the external get box notifications endpoint with unknown query parameters$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithUnknownQuerryParameters() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("3b8e4dd3-a029-4301-a912-1220f3196387", "unknown", "PENDING", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid status query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidStatusQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("3b8e4dd3-a029-4301-a912-1220f3196387", "status", "foobar", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid fromDate query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidDateToQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("3b8e4dd3-a029-4301-a912-1220f3196387", "status", "PENDING", "fromDate", "2020-06-16T17:13:00.000+123", "toDate", "2020-07-16T17:13:00.000");
    }

    @When("^I make a request to the external get box notifications endpoint with an invalid toDate query parameter value$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointWithAnInvalidToDateQueryParameterValue() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters("3b8e4dd3-a029-4301-a912-1220f3196387", "status", "PENDING", "fromDate", "2020-06-16T17:13:00.000", "toDate", "2020-07-16T17:13:00.000+123");
    }

    @When("^I make a request to the external get box notifications endpoint$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpoint() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotifications("3b8e4dd3-a029-4301-a912-1220f3196387", "status", "PENDING");
    }

    @When("^I make a request to the external get box notifications endpoint for pending status notifications$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForPendingStatusNotifications() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotifications(pushPullNotificationsApiSteps.getNewBoxId());
    }

    @When("^I make a request to the external get box notifications endpoint for acknowledged status notifications$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForAcknowledgedStatusNotifications() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(pushPullNotificationsApiSteps.getNewBoxId(), "status", "ACKNOWLEDGED");
    }

    @When("^I make a request to the external get box notifications endpoint with a box ID that belongs to another client ID$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForWithANonBelongingBoxId() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter("1e163398-2c83-405d-a11b-bfa671013800", "status", "PENDING");
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

    @When("^I make a request to the external put acknowledge notifications endpoint with a box ID that belongs to another client ID$")
    public void iMakeARequestToTheExternalPutAcknowledgeNotificationsEndpointWithANonBelongingId() {
        pushPullNotificationsApiSteps.iMakeACallToTheExternalPutAcknowledgeNotifications("1e163398-2c83-405d-a11b-bfa671013800");
        //pushPullNotificationsApiSteps.iMakeACallToTheExternalPutAcknowledgeNotifications("a5e3203d-a57e-4787-ba72-2dbfc294455f");
        //OLD BOX ID CAN BE DELETED
    }

    @When("^I make a request to the external get box notifications endpoint for \"(.*)\" notifications with valid date query parameter values$")
    public void iMakeARequestToTheExternalGetBoxNotificationsEndpointForNotificationsWithValidDateQueryParameterValues(String statusValue) {
        String fromDateValue = generateCurrentDate();
        String toDateValue = generateFutureDate();
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters(pushPullNotificationsApiSteps.getNewBoxId(), "status", statusValue, "fromDate", fromDateValue, "toDate", toDateValue);
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


    // OK - 2XXs
    @Then("^A box is successfully generated$")
    public void aBoxIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertBoxGenerated();
    }

    @Then("^the new box is successfully returned$")
    public void theNewBoxIsSuccessfullyReturned() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNewBoxExists();
    }

    @Then("^I get a successful response where all boxes are returned$")
    public void iGetASuccessfulResponseWhereAllBoxesAreReturned() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertAllBoxes();
    }

    @Then("^I get a successful response with default and client managed boxes displayed$")
    public void iGetASuccessfulResponseWithDefaultAndClientManagedBoxesDisplayed() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertListOfBoxes();
    }

    @Then("^I get a successful response with no boxes displayed$")
    public void iGetASuccessfulResponseWithNoBoxesDisplayed() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNoBoxes();
    }

    @Then("^I get a successful response with a successful true response message$")
    public void iGetASuccessfulResponseWithASuccessfulTrueResponseMessage() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("{\"successful\":true}");
    }

    @Then("^I get a successful response with the correct secret returned$")
    public void iGetASuccessfulResponseWithTheCorrectSecretReturned() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("[{\"value\":\"FUHT3LOIF7EGOUP6OO7L3Q6GVNNJOZUM\"}]");
    }

    @Then("^I can set a callback url$")
    public void iCanSetACallbackUrl() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertCallbackUrlUpdated();
    }

    @Then("^I get an invalid callback URL response$")
    public void iGetAValidationFailedResponse() {
        responseSteps.expectedHttpStatusCode(200);
        responseSteps.expectedJsonResponseBody("{\"successful\":false,\"errorMessage\":\"Invalid callback URL. Check the information you have provided is correct.\"}");
    }

    @Then("^I get a successful response with the correct notification details for the new box$")
    public void iGetASuccessfulResponseWithTheCorrectNotificationDetailsForTheNewBox() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForTheNewBox();
    }

    @Then("^I get a successful response with the correct notification details$")
    public void iGetASuccessfulResponseWithTheCorrectNotificationDetails() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForPendingStatusAndDateParameters();
    }

    @Then("^I get a successful response with the correct acknowledged notification details$")
    public void iGetASuccessfulResponseWithTheCorrectAcknowledgedNotificationDetails() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForAcknowledgedStatusAndDateParameters();
    }

    @Then("^I get a successful response with the correct max size notification details for the new box$")
    public void iGetASuccessfulResponseWithTheCorrectMaxSIzeNotificationDetailsForTheNewBox() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectMaxSizeNotificationDetailsForTheNewBox();
    }

    @Then("^I get a successful response with pending notifications")
    public void iGetASuccessfulResponseWithPendingNotifications() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasPendingStatusNotifications();
    }

    @Then("^I get a successful response with acknowledged notifications")
    public void iGetASuccessfulResponseWithAcknowledgedNotifications() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasAcknowledgedStatusNotifications();
    }

    @Then("^the existing client managed box is successfully updated$")
    public void theExistingClientManagedBoxIsSuccessfullyUpdated() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNewClientManagedBoxGenerated();
    }

    @Then("^I get a validate \"([^\"]*)\" response$")
    public void iGetAValidateTrueResponse(boolean response) {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.asserValidateClientManagedBoxResponse(response);
    }

    @Then("^I get a validate callback URL true response$")
    public void iGetAValidateCallbackUrlTrueResponse() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertValidateCallbackUrlResponse(true);
    }

    @Then("^I get a validate callback URL false response$")
    public void iGetAValidateCallbackUrlFalseResponse() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertInvalidCallbackUrlResponse(false);
    }

    @Then("^I get a validate false response$")
    public void iGetAValidateFalseResponse() {
        responseSteps.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.asserValidateClientManagedBoxResponse(false);
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

    @Then("^I get a successful response with notifications now acknowledged")
    public void iGetASuccessfulResponseWithNotificationsNowAcknowledged() {
        responseSteps.expectedHttpStatusCode(204);
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(pushPullNotificationsApiSteps.getNewBoxId(), "status", "ACKNOWLEDGED");
        pushPullNotificationsApiSteps.hasAcknowledgedStatusNotifications();
    }

    @Then("^A notification is successfully generated$")
    public void aNotificationsIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(201) ;
        pushPullNotificationsApiSteps.assertNotificationCreated();
    }

    @Then("^A notification with a confirmation URL is successfully generated$")
    public void aNotificationWithAConfirmationUrlIsSuccessfullyGenerated() {
        responseSteps.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNotificationWithConfirmationUrlCreated();
    }

    // Bad Requests - 400s
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
    public void iGetABadRequestResponseDueToMissingBoxName() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Expecting boxName in request body");
    }

    @Then("^I get a bad request response due to missing callback URL$")
    public void iGetABadRequestResponseDueToMissingCallbackUrl() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Expecting boxName in request body");
    }

    @Then("^I get a bad request response due to message version invalid")
    public void iGetABadRequestResponseDueToMesssageVersionInvalid() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseSteps.expectedJsonMessage("Message version is invalid");
    }


    // Unauthorised - 401s
    @Then("^I get an unauthorised response due to an invalid bearer token$")
    public void iGetAnUnauthorisedResponseDueToAnInvalidBearerToken() {
        responseSteps.expectedHttpStatusCode(401);
        responseSteps.expectedJsonErrorCode("UNAUTHORISED");
        responseSteps.expectedJsonMessage("Invalid bearer token");
    }

    @Then("^I get an unauthorised response due to invalid authentication information provided$")
    public void iGetAnUnauthorisedResponseDueToAnInvalidAuthenticationInformationProfivded() {
        responseSteps.expectedHttpStatusCode(401);
        responseSteps.expectedJsonErrorCode("INVALID_CREDENTIALS");
        responseSteps.expectedJsonMessage("Invalid Authentication information provided");
    }

    @Then("^I get an unauthorised response due to client ID mismatch$")
    public void iGetAnUnauthorisedResponseDueToClientIdMismatch() {
        responseSteps.expectedHttpStatusCode(401);
        responseSteps.expectedJsonErrorCode("UNAUTHORISED");
        responseSteps.expectedJsonMessage("Client Id did not match");
    }

    // Forbidden - 403s
    @Then("^I get a forbidden response$")
    public void iGetAForbiddenResponse() {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode("FORBIDDEN");
        responseSteps.expectedJsonMessage("Access denied");
    }

    private void iGetAForbiddenResponseDueToAuthorizationFailed() {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode("FORBIDDEN");
        responseSteps.expectedJsonMessage("Authorisation failed");
    }

    // Not Found - 404s
    @Then("^I get a not found response due to box not found$")
    public void iGetANotFoundResponseDueToBoxNotFound() {
        responseSteps.expectedHttpStatusCode(404);
        responseSteps.expectedJsonErrorCode("BOX_NOT_FOUND");
        responseSteps.expectedJsonMessage("Box not found");
    }

    // Request Entity Too Large - 413s
    @Then("^I get a request entity too large response$")
    public void iGetARequestEntityTooLargeResponse() {
        responseSteps.expectedHttpStatusCode(413);
        responseSteps.expectedJsonErrorCode("UNKNOWN_ERROR");
        responseSteps.expectedJsonMessage("Request Entity Too Large");
    }

    private String generateCurrentDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return date.format(formatter);
    }

    private String generateFutureDate() {
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return date.format(formatter);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////



    @Then("^I get a bad request response due to an invalid request payload$")
    public void iGetABadRequestResponseDueToAnInvalidRequestPayload() {
        iGetAnInvalidRequestPayloadResponse();
        responseSteps.expectedJsonMessage("JSON body is invalid against expected format");
    }

    @Then("^I get a bad request response due to request contains more than 5 headers$")
    public void iGetABadRequestResponseDueToRequestContainsMoreThan5Headers() {
        iGetAnInvalidRequestPayloadResponse();
        responseSteps.expectedJsonMessage("Request contains more than 5 private headers");
    }

    @Then("^I get a bad request response due to invalid or unknown query parameters$")
    public void iGetABadRequestResponseDueToInvalidOrUnknownQueryParameters() {
        iGetAnInvalidRequestPayloadResponse();
        responseSteps.expectedJsonMessage("Invalid / Unknown query parameter provided");
    }

    @Then("^I get a matching resource not found response$")
    public void iGetAMatchingResourceNotFoundResponse() {
        iGetNotFoundResponse("MATCHING_RESOURCE_NOT_FOUND");
        responseSteps.expectedJsonMessage("A resource with the name in the request can not be found in the API");
    }

    @Then("^I get a forbidden response due to invalid scope$")
    public void iGetAForbiddenResponseDueToInvalidResponse() {
        iGetAForbiddenResponse("INVALID_SCOPE");
        responseSteps.expectedJsonMessage("Can not access the required resource. Ensure this token has all the required scopes.");
    }

    @Then("^I get an unacceptable response due to a missing accept header$")
    public void iGetAnUnacceptableResponseDueToAMissingAcceptHeader() {
        iGetAnAcceptHeaderInvalidResponse();
    }

    @Then("^I get an unacceptable response due to an invalid accept header$")
    public void iGetANotAcceptableResponseDueToAnInavlidAcceptHeader() {
        iGetAnAcceptHeaderInvalidResponse();
    }

    @Then("^I get the JSON message \"([^\"]*)\"$")
    public void iGetTheJsonMessage(final String jsonMessage) {
        responseSteps.expectedJsonMessage(jsonMessage);
    }

    @Then("^I get a successful response$")
    public void iGetASuccessfulResponse() {
        responseSteps.expectedHttpStatusCode(200);
    }

    @Then("^I get a standard bad request response")
    public void iGetAStandardBadRequestResponse() {
        responseSteps.expectedHttpStatusCode(400);
    }

    private void iGetAnInvalidRequestPayloadResponse() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
    }

    private void iGetAForbiddenResponse(String expectedErrorCode) {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode(expectedErrorCode);
    }

    private void iGetNotFoundResponse(String expectedErrorCode) {
        responseSteps.expectedHttpStatusCode(404);
        responseSteps.expectedJsonErrorCode(expectedErrorCode);
    }

    private void iGetAnAcceptHeaderInvalidResponse() {
        responseSteps.expectedHttpStatusCode(406);
        responseSteps.expectedJsonErrorCode("ACCEPT_HEADER_INVALID");
        responseSteps.expectedJsonMessage("The accept header is missing or invalid");
    }

    @Then("^I get an unsupported media type response$")
    public void iGetAnUnsupportedMediaTypeResponse() {
        responseSteps.expectedHttpStatusCode(415);
        responseSteps.expectedJsonMessage("Expecting text/json or application/json body");
    }

    @Then("^I get an unsupported media type response version two$")
    public void iGetAnUnsupportedMediaTypeResponseVersionTwo() {
        responseSteps.expectedHttpStatusCode(415);
        responseSteps.expectedJsonErrorCode("BAD_REQUEST");
        responseSteps.expectedJsonMessage("Expecting text/json or application/json body");
    }

    @Then("^I get an unsupported media type response due to content type not supported$")
    public void iGetAnUnsupportedMediaTypeResponseDueToContentTypeNoSupported() {
        responseSteps.expectedHttpStatusCode(415);
        responseSteps.expectedJsonErrorCode("BAD_REQUEST");
        responseSteps.expectedJsonMessage("Content Type not Supported");
    }
}