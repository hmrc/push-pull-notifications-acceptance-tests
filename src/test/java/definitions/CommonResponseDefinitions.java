package definitions;

import io.cucumber.java.en.Then;

public class CommonResponseDefinitions extends CommonDefinitions {

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
