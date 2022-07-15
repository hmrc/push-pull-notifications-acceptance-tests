package definitions;

import io.cucumber.java.en.Then;

import static org.hamcrest.CoreMatchers.is;

public class CommonResponseDefinitions extends CommonDefinitions {

    @Then("^I get a bad request response due to an invalid request payload$")
    public void iGetABadRequestResponseDueToAnInvalidRequestPayload() {
        iGetAnInvalidRequestPayloadResponse();
        responseSteps.expectedJsonMessage("JSON body is invalid against expected format");
    }

    @Then("^I get a bad request response due to invalid or unknown query parameters$")
    public void iGetABadRequestResponseDueToInvalidOrUnknownQueryParameters() {
        iGetAnInvalidRequestPayloadResponse();
        responseSteps.expectedJsonMessage("Invalid / Unknown query parameter provided");
    }

//    @Then("^I get an unauthorised response due to missing credentials$")
//    public void iGetAnUnauthorisedResponseDueToMissingCredentials() {
//        iGetAnUnauthorisedResponse("MISSING_CREDENTIALS");
//        responseSteps.expectedJsonMessage("Authentication information is not provided");
//    }

//    @Then("^I get an unauthorised response due to invalid credentials$")
//    public void iGetAnUnauthorisedResponseDueToInvalidCredentials() {
//        iGetAnUnauthorisedResponse("INVALID_CREDENTIALS");
//        responseSteps.expectedJsonMessage("Invalid Authentication information provided");
//    }

//    @Then("^I get an unauthorised response due to an incorrect bearer token type$")
//    public void iGetAnUnauthorisedResponseDueToAnIncorrectBearerTokenType() {
//        iGetAnUnauthorisedResponse("INVALID_CREDENTIALS");
//        responseSteps.expectedJsonMessage("Invalid Authentication information provided");
//    }

//    @Then("^I get a not found response due to the requested resource can not be found")
//    public void iGetANotFoundResponseDueToTheRequestedRescourceCanNotBeFound() {
//        iGetNotFoundResponse("NOT_FOUND");
//        responseSteps.expectedJsonMessage("The requested resource could not be found.");
//    }

    //TODO - Move
    @Then("^I get a matching resource not found response$")
    public void iGetAMatchingResourceNotFoundResponse() {
        iGetNotFoundResponse("MATCHING_RESOURCE_NOT_FOUND");
        responseSteps.expectedJsonMessage("A resource with the name in the request can not be found in the API");
    }

    //TODO - Move
    @Then("^I get a forbidden response due to invalid scope$")
    public void iGetAForbiddenResponseDueToInvalidResponse() {
        iGetAForbiddenResponse("INVALID_SCOPE");
        responseSteps.expectedJsonMessage("Can not access the required resource. Ensure this token has all the required scopes.");
    }

//    @Then("^I get a resource forbidden response due to IP not whitelisted")
//    public void iGetAResourceForbiddenResponseDueToIPNotWhitelisted() {
//        iGetAForbiddenResponse("FORBIDDEN");
//        responseSteps.expectedJsonMessage("This endpoint is not available");
//    }

//    @Then("^I get a resource forbidden response due to no API subscription")
//    public void iGetAResourceForbiddenResponseDueToNoApiSubscription() {
//        iGetAForbiddenResponse("RESOURCE_FORBIDDEN");
//        responseSteps.expectedJsonMessage("The application is not subscribed to the API which it is attempting to invoke");
//    }

//    @Then("^I get a resource forbidden response due to the application being blocked")
//    public void iGetAResourceForbiddenResponseDueToTheApplicationBeingBlocked() {
//        iGetAForbiddenResponse("RESOURCE_FORBIDDEN");
//        responseSteps.expectedJsonMessage("The application is blocked");
//    }

//    @Then("^I get a method not allowed response$")
//    public void iGetAMethodNotAllowedResponseDueToUnsupportedHeader() {
//        iGetAMethodNotAllowedResponse();
//    }

//    @Then("^I get a not found response due to missing data")
//    public void iGetANotFoundResponseDueToMissingData() {
//        iGetNotFoundResponse("NOT_FOUND");
//        responseSteps.expectedJsonMessage("Resource was not found");
//    }

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

//    @Then("^I get a successful accepted response$")
//    public void iGetASuccessfulAcceptedResponse() {
//        responseSteps.expectedHttpStatusCode(202);
//    }

//    @Then("^I get a successful no content response$")
//    public void iGetASuccessfulNoContentResponse() {
//        responseSteps.expectedHttpStatusCode(204);
//    }

    @Then("^I get a standard bad request response")
    public void iGetAStandardBadRequestResponse() {
        responseSteps.expectedHttpStatusCode(400);
    }

    private void iGetAnInvalidRequestPayloadResponse() {
        responseSteps.expectedHttpStatusCode(400);
        responseSteps.expectedJsonErrorCode("INVALID_REQUEST_PAYLOAD");
    }

//    @Then("^I get a bad request response due to no JSON payload")
//    public void iGetABadrequestResponseDueToNoJsonPayload() {
//        responseSteps.expectedHttpStatusCode(400);
//        responseSteps.expectedJsonResponse(400, is("bad request"));
//    }

//    @Then("^I get a bad request response due to invalid JSON")
//    public void iGetABadRequestResponseDueToInvalidJson() {
//        responseSteps.expectedHttpStatusCode(400);
//        responseSteps.expectedJsonResponse(400, is("bad request"));
//    }

//    @Then("^I get an unauthorised response$")
//    public void iGetAnAuthorisedResponse() {
//        responseSteps.expectedHttpStatusCode(401);
//    }

//    private void iGetAnUnauthorisedResponse(String expectedErrorCode) {
//        responseSteps.expectedHttpStatusCode(401);
//        responseSteps.expectedJsonErrorCode(expectedErrorCode);
//    }

//    @Then("^I get a forbidden response$")
//    public void iGetAForbiddenResponse() {
//        responseSteps.expectedHttpStatusCode(403);
//    }

    private void iGetAForbiddenResponse(String expectedErrorCode) {
        responseSteps.expectedHttpStatusCode(403);
        responseSteps.expectedJsonErrorCode(expectedErrorCode);
    }

//    @Then("^I get a not found response$")
//    public void iGetANotFoundResponse() {
//        responseSteps.expectedHttpStatusCode(404);
//    }

    private void iGetNotFoundResponse(String expectedErrorCode) {
        responseSteps.expectedHttpStatusCode(404);
        responseSteps.expectedJsonErrorCode(expectedErrorCode);
    }

//    private void iGetAMethodNotAllowedResponse() {
//        responseSteps.expectedHttpStatusCode(405);
//    }

    private void iGetAnAcceptHeaderInvalidResponse() {
        responseSteps.expectedHttpStatusCode(406);
        responseSteps.expectedJsonErrorCode("ACCEPT_HEADER_INVALID");
        responseSteps.expectedJsonMessage("The accept header is missing or invalid");
    }

//    private void iGetAPreConditionFailedResponse(final String expectedMessage) {
//        responseSteps.expectedHttpStatusCode(412);
//        responseSteps.expectedJsonErrorCode("MISSING_OR_INVALID_HEADERS");
//        responseSteps.expectedJsonMessage(expectedMessage);
//    }

    @Then("^I get an unsupported media type response$")
    public void iGetAnUnsupportedMediaTypeResponse() {
        responseSteps.expectedHttpStatusCode(415);
        responseSteps.expectedJsonMessage("Expecting text/json or application/json body");
    }

//    @Then("^I get an unsupported XML media type response$")
//    public void iGetAnUnsupportedXmlMediaTypeResponse() {
//        responseSteps.expectedHttpStatusCode(415);
//        responseSteps.expectedJsonMessage("Expecting xml body");
//    }

//    @Then("^I get an XML unsupported media type response$")
//    public void iGetAnXmlUnsupportedMediaTypeResponse() {
//        responseSteps.expectedHttpStatusCode(415);
//        //Below step not working because response is returned in JSON and NOT XML
//        //responseSteps.expectedXmlMessage("Expecting xml body");
//    }

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

//    @Then("^I get a service temporarily unavailable response")
//    public void iGetAServiceTemporarilyUnavailableResponse() {
//        responseSteps.expectedHttpStatusCode(503);
//        responseSteps.expectedJsonErrorCode("SERVER_ERROR");
//        responseSteps.expectedJsonMessage("Service unavailable");
//    }
}
