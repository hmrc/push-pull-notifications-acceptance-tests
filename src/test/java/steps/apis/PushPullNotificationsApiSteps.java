package steps.apis;

import apis.PushPullNotificationsCommonApi;
import helpers.apis.ContentTypeHeaderHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import utilities.configuration.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

public class PushPullNotificationsApiSteps extends ResponseSteps {

    protected Configuration config = new Configuration();

    @Steps
    ContentTypeHeaderHelper contentTypeHeaderHelper;

    @Steps
    private PushPullNotificationsCommonApi pushPullNotificationsApiSteps;

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
    public void iHaveAGeneratedNotificationInAnAcknowledgedStatus() {
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
        //Thread.Sleep Included to allow enough time for the notification to be processed to acknowledged
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
    @Then("^I get a successful response$")
    public void iGetASuccessfulResponse() {
        responseHelper.expectedHttpStatusCode(200);
    }

    @Then("^A box is successfully generated$")
    public void aBoxIsSuccessfullyGenerated() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertBoxGenerated();
    }

    @Then("^the new box is successfully returned$")
    public void theNewBoxIsSuccessfullyReturned() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertNewBoxExists();
    }

    @Then("^I get a successful response where all boxes are returned$")
    public void iGetASuccessfulResponseWhereAllBoxesAreReturned() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertAllBoxes();
    }

    @Then("^I get a successful response with a successful true response message$")
    public void iGetASuccessfulResponseWithASuccessfulTrueResponseMessage() {
        responseHelper.expectedHttpStatusCode(200);
        responseHelper.expectedJsonResponseBody("{\"successful\":true}");
    }

    @Then("^I get a successful response with the correct secret returned$")
    public void iGetASuccessfulResponseWithTheCorrectSecretReturned() {
        responseHelper.expectedHttpStatusCode(200);
        responseHelper.expectedJsonResponseBody("[{\"value\":\"FUHT3LOIF7EGOUP6OO7L3Q6GVNNJOZUM\"}]");
    }

    @Then("^I can set a callback url$")
    public void iCanSetACallbackUrl() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertCallbackUrlUpdated();
    }

    @Then("^I get an invalid callback URL response$")
    public void iGetAValidationFailedResponse() {
        responseHelper.expectedHttpStatusCode(200);
        responseHelper.expectedJsonResponseBody("{\"successful\":false,\"errorMessage\":\"Invalid callback URL. Check the information you have provided is correct.\"}");
    }

    @Then("^I get a successful response with the correct notification details for the new box$")
    public void iGetASuccessfulResponseWithTheCorrectNotificationDetailsForTheNewBox() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForTheNewBox();
    }

    @Then("^I get a successful response with the correct notification details$")
    public void iGetASuccessfulResponseWithTheCorrectNotificationDetails() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForPendingStatusAndDateParameters();
    }

    @Then("^I get a successful response with the correct acknowledged notification details$")
    public void iGetASuccessfulResponseWithTheCorrectAcknowledgedNotificationDetails() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectNotificationDetailsForAcknowledgedStatusAndDateParameters();
    }

    @Then("^I get a successful response with the correct max size notification details for the new box$")
    public void iGetASuccessfulResponseWithTheCorrectMaxSIzeNotificationDetailsForTheNewBox() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasCorrectMaxSizeNotificationDetailsForTheNewBox();
    }

    @Then("^I get a successful response with pending notifications")
    public void iGetASuccessfulResponseWithPendingNotifications() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasPendingStatusNotifications();
    }

    @Then("^I get a successful response with acknowledged notifications")
    public void iGetASuccessfulResponseWithAcknowledgedNotifications() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.hasAcknowledgedStatusNotifications();
    }

    @Then("^I get a validate \"([^\"]*)\" response$")
    public void iGetAValidateTrueResponse(boolean response) {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.asserValidateClientManagedBoxResponse(response);
    }

    @Then("^I get a validate callback URL true response$")
    public void iGetAValidateCallbackUrlTrueResponse() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertValidateCallbackUrlResponse(true);
    }

    @Then("^I get a validate callback URL false response$")
    public void iGetAValidateCallbackUrlFalseResponse() {
        responseHelper.expectedHttpStatusCode(200);
        pushPullNotificationsApiSteps.assertInvalidCallbackUrlResponse(false);
    }

    @Then("^A new box is successfully generated$")
    public void aNewBoxIsSuccessfullyGenerated() {
        responseHelper.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNewBoxGenerated();
    }

    @Then("^A notification is successfully generated$")
    public void aNotificationsIsSuccessfullyGenerated() {
        responseHelper.expectedHttpStatusCode(201) ;
        pushPullNotificationsApiSteps.assertNotificationCreated();
    }

    @Then("^A notification with a confirmation URL is successfully generated$")
    public void aNotificationWithAConfirmationUrlIsSuccessfullyGenerated() {
        responseHelper.expectedHttpStatusCode(201);
        pushPullNotificationsApiSteps.assertNotificationWithConfirmationUrlCreated();
    }

    @Then("^I get a successful response with notifications now acknowledged")
    public void iGetASuccessfulResponseWithNotificationsNowAcknowledged() {
        responseHelper.expectedHttpStatusCode(204);
        pushPullNotificationsApiSteps.iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(pushPullNotificationsApiSteps.getNewBoxId(), "status", "ACKNOWLEDGED");
        pushPullNotificationsApiSteps.hasAcknowledgedStatusNotifications();
    }

    // Bad Requests - 400s
    @Then("^I get a standard bad request response")
    public void iGetAStandardBadRequestResponse() {
        responseHelper.expectedHttpStatusCode(400);
    }

    @Then("^I get a bad request response due to an invalid request payload$")
    public void iGetABadRequestResponseDueToAnInvalidRequestPayload() {
        iGetAnInvalidRequestPayloadResponse();
        responseHelper.expectedJsonMessage("JSON body is invalid against expected format");
    }

    @Then("^I get a bad request response due to request contains more than 5 headers$")
    public void iGetABadRequestResponseDueToRequestContainsMoreThan5Headers() {
        iGetAnInvalidRequestPayloadResponse();
        responseHelper.expectedJsonMessage("Request contains more than 5 private headers");
    }

    @Then("^I get a bad request response due to invalid or unknown query parameters$")
    public void iGetABadRequestResponseDueToInvalidOrUnknownQueryParameters() {
        iGetAnInvalidRequestPayloadResponse();
        responseHelper.expectedJsonMessage("Invalid / Unknown query parameter provided");
    }

    @Then("^I get a bad request response due to the box ID not being a valid UUID$")
    public void iGetABadRequestResponseDueToTheBoxIdNotBeingAValidUuid() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("BAD_REQUEST");
        responseHelper.expectedJsonMessage("Box ID is not a UUID");
    }

    @Then("^I get a bad request response due to content type not supported or message syntax invalid$")
    public void iGetABadRequestResponseDueToContentTypeNotSupportedOrMessageSyntaxInvalid() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Message syntax is invalid");
    }

    @Then("^I get a bad request response due missing parameter values$")
    public void iGetABadRequestResponseDueToMissingParameterValues() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Expecting boxName and clientId in request body");
    }

    @Then("^I get a bad request response due to invalid box name query parameter$")
    public void iGetABadRequestResponseDueToInvalidBoxNameQueryParameter() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonMessage("Must specify both boxName and clientId query parameters or neither");
    }

    @Then("^I get a bad request response due to invalid client ID query parameter$")
    public void iGetABadRequestResponseDueToInvalidClientIdQueryParameter() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonMessage("Must specify both boxName and clientId query parameters or neither");
    }

    @Then("^I get an invalid request payload response due to an invalid status parameter provided$")
    public void iGetAnInvalidRequestPayloadResponseDueToAnInvalidStatusQueryParameterProvided() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Invalid Status parameter provided");
    }

    @Then("^I get an invalid request payload response due to an unparsable date value parameter provided$")
    public void iGetAnInvalidRequestPayloadResponseDueToAnUnparsableDateValueQueryParameterProvided() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Unparsable DateValue Param provided");
    }

    @Then("^I get a bad request response due to missing client ID$")
    public void iGetABadRequestResponseDueToMissingClientIdAndCallbackUrl() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("clientId is required");
    }

    //TODO - FIND USAGES - RELATED TO CMB
    @Then("^I get a bad request response due to missing box ID or client ID$")
    public void iGetABadRequestResponseDueToMissingBoxIdOrClientId() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Expecting boxId and clientId in request body");
    }

    @Then("^I get a bad request response due to missing box name$")
    public void iGetABadRequestResponseDueToMissingBoxName() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Expecting boxName in request body");
    }

    @Then("^I get a bad request response due to missing callback URL$")
    public void iGetABadRequestResponseDueToMissingCallbackUrl() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Expecting boxName in request body");
    }

    @Then("^I get a bad request response due to message version invalid")
    public void iGetABadRequestResponseDueToMessageVersionInvalid() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
        responseHelper.expectedJsonMessage("Message version is invalid");
    }

    // Unauthorised - 401s
    @Then("^I get an unauthorised response due to an invalid bearer token$")
    public void iGetAnUnauthorisedResponseDueToAnInvalidBearerToken() {
        responseHelper.expectedHttpStatusCode(401);
        responseHelper.expectedJsonErrorCode("UNAUTHORISED");
        responseHelper.expectedJsonMessage("Invalid bearer token");
    }

    @Then("^I get an unauthorised response due to invalid authentication information provided$")
    public void iGetAnUnauthorisedResponseDueToAnInvalidAuthenticationInformationProivded() {
        responseHelper.expectedHttpStatusCode(401);
        responseHelper.expectedJsonErrorCode("INVALID_CREDENTIALS");
        responseHelper.expectedJsonMessage("Invalid Authentication information provided");
    }

    @Then("^I get an unauthorised response due to client ID mismatch$")
    public void iGetAnUnauthorisedResponseDueToClientIdMismatch() {
        responseHelper.expectedHttpStatusCode(401);
        responseHelper.expectedJsonErrorCode("UNAUTHORISED");
        responseHelper.expectedJsonMessage("Client Id did not match");
    }

    // Forbidden - 403s
    @Then("^I get a forbidden response$")
    public void iGetAForbiddenResponse() {
        responseHelper.expectedHttpStatusCode(403);
        responseHelper.expectedJsonErrorCode("FORBIDDEN");
        responseHelper.expectedJsonMessage("Access denied");
    }

    @Then("^I get a forbidden response due to invalid scope$")
    public void iGetAForbiddenResponseDueToInvalidResponse() {
        iGetAForbiddenResponse("INVALID_SCOPE");
        responseHelper.expectedJsonMessage("Can not access the required resource. Ensure this token has all the required scopes.");
    }

    private void iGetAForbiddenResponseDueToAuthorizationFailed() {
        responseHelper.expectedHttpStatusCode(403);
        responseHelper.expectedJsonErrorCode("FORBIDDEN");
        responseHelper.expectedJsonMessage("Authorisation failed");
    }

    // Not Found - 404s
    @Then("^I get a not found response due to box not found$")
    public void iGetANotFoundResponseDueToBoxNotFound() {
        responseHelper.expectedHttpStatusCode(404);
        responseHelper.expectedJsonErrorCode("BOX_NOT_FOUND");
        responseHelper.expectedJsonMessage("Box not found");
    }

    @Then("^I get a matching resource not found response$")
    public void iGetAMatchingResourceNotFoundResponse() {
        iGetNotFoundResponse("MATCHING_RESOURCE_NOT_FOUND");
        responseHelper.expectedJsonMessage("A resource with the name in the request can not be found in the API");
    }

    // Invalid or Missing Request Header - 406s
    @Then("^I get an unacceptable response due to a missing accept header$")
    public void iGetAnUnacceptableResponseDueToAMissingAcceptHeader() {
        iGetAnAcceptHeaderInvalidResponse();
    }

    @Then("^I get an unacceptable response due to an invalid accept header$")
    public void iGetANotAcceptableResponseDueToAnInvalidAcceptHeader() {
        iGetAnAcceptHeaderInvalidResponse();
    }

    // Request Entity Too Large - 413s
    @Then("^I get a request entity too large response$")
    public void iGetARequestEntityTooLargeResponse() {
        responseHelper.expectedHttpStatusCode(413);
        responseHelper.expectedJsonErrorCode("UNKNOWN_ERROR");
        responseHelper.expectedJsonMessage("Request Entity Too Large");
    }

    // Unsupported Media Type - 415s
    @Then("^I get an unsupported media type response$")
    public void iGetAnUnsupportedMediaTypeResponse() {
        responseHelper.expectedHttpStatusCode(415);
        responseHelper.expectedJsonMessage("Expecting text/json or application/json body");
    }

    @Then("^I get an unsupported media type response version two$")
    public void iGetAnUnsupportedMediaTypeResponseVersionTwo() {
        responseHelper.expectedHttpStatusCode(415);
        responseHelper.expectedJsonErrorCode("BAD_REQUEST");
        responseHelper.expectedJsonMessage("Expecting text/json or application/json body");
    }

    @Then("^I get an unsupported media type response due to content type not supported$")
    public void iGetAnUnsupportedMediaTypeResponseDueToContentTypeNoSupported() {
        responseHelper.expectedHttpStatusCode(415);
        responseHelper.expectedJsonErrorCode("BAD_REQUEST");
        responseHelper.expectedJsonMessage("Content Type not Supported");
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

    @Then("^I get the JSON message \"([^\"]*)\"$")
    public void iGetTheJsonMessage(final String jsonMessage) {
        responseHelper.expectedJsonMessage(jsonMessage);
    }

    private void iGetAnInvalidRequestPayloadResponse() {
        responseHelper.expectedHttpStatusCode(400);
        responseHelper.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
    }

    private void iGetAForbiddenResponse(String expectedErrorCode) {
        responseHelper.expectedHttpStatusCode(403);
        responseHelper.expectedJsonErrorCode(expectedErrorCode);
    }

    private void iGetNotFoundResponse(String expectedErrorCode) {
        responseHelper.expectedHttpStatusCode(404);
        responseHelper.expectedJsonErrorCode(expectedErrorCode);
    }

    private void iGetAnAcceptHeaderInvalidResponse() {
        responseHelper.expectedHttpStatusCode(406);
        responseHelper.expectedJsonErrorCode("ACCEPT_HEADER_INVALID");
        responseHelper.expectedJsonMessage("The accept header is missing or invalid");
    }
}