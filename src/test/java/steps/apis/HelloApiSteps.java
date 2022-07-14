package steps.apis;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static org.hamcrest.CoreMatchers.is;

public class HelloApiSteps extends CommonApiSteps {

    @Step
    public void callGetHelloWorld() {
        response(
            given()
                .spec(specification())
                .get(baseApiUrl() + "/hello/world")
            .then()
        );
    }

    @Step
    public void CallGetHelloWorldWithInvalidContext() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/xxxhello/world")
                        .then()
        );
    }

    @Step
    public void incorrectCallGetHelloWorld() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/hello/XXXworld")
                        .then()
        );
    }

    @Step
    public void invalidMethodCallGetHelloWorld() {
        response(
                given()
                        .spec(specification())
                        .head(baseApiUrl() + "/hello/world")
                        .then()
        );
    }

    @Step
    public void callGetHelloApplication() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/hello/application")
                        .then()
        );
    }

    @Step
    public void callGetHelloUser() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/hello/user")
                        .then()
        );
    }

    @Step
    public void expectedJsonMessage(String expectedMessage) {
        commonResponseSteps.expectedContentType(JSON);
        response().body("message", is(expectedMessage));
    }
}
