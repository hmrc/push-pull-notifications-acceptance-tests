package helpers.apis;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import org.hamcrest.Matcher;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static org.hamcrest.CoreMatchers.is;

public class ResponseHelper {

    private ValidatableResponse response;

    public void response(ValidatableResponse response) {
        this.response = response;
    }

    public ValidatableResponse response() {
        return response;
    }

    @Step
    public void expectedContentType(ContentType expectedContentType) {
        response.contentType(expectedContentType);
    }

    @Step
    public void expectedHttpStatusCode(int expectedStatus) {
        response.statusCode(expectedStatus);
    }

    @Step
    public void expectedJsonErrorCode(String expectedCode) {
        expectedContentType(JSON);
        response.body("code", is(expectedCode));
    }

    @Step
    public void expectedJsonMessage(String expectedMessage) {
        expectedContentType(JSON);
        response.body("message", is(expectedMessage));
    }

    @Step
    public void expectedXmlMessage(String expectedMessage) {
        expectedContentType(XML);
        response.body("message", is(expectedMessage));
    }

    @Step
    public void expectedTextMessage(String expectedMessage) {
        response.body(is(expectedMessage));
    }

    @Step
    public void expectedJsonResponse(int expectedStatusCode, Matcher<String> expectedMessageMatcher) {
        expectedContentType(JSON);
        response.body("statusCode", is(expectedStatusCode));
        response.body("message", expectedMessageMatcher);
    }

    @Step
    public void expectedJsonResponseBody(String expectedResponseBody) {
        expectedContentType(JSON);
        response.body(is(expectedResponseBody));
    }
}
