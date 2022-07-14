package definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.apis.HelloApiSteps;

public class HelloApiDefinitions extends CommonDefinitions {

    @Steps
    private HelloApiSteps helloApiSteps;

    @When("^I make a call to the get hello world endpoint$")
    public void iMakeACallToTheGetHelloWorldEndpoint() {
        helloApiSteps.callGetHelloWorld();
    }

    @When("^I make a call to the get hello application endpoint$")
    public void iMakeACallToGetTheHelloApplicationEndpoint() {
        helloApiSteps.callGetHelloApplication();
    }

    @When("^I make a call to the get hello user endpoint$")
    public void iMakeACallToTheGetHelloUserEndpoint() {
        helloApiSteps.callGetHelloUser();
    }

    @Then("^I get a successful response with the message (.*)$")
    public void iGetAResponseWithTheMessage(String expectedMessage) {
        responseSteps.expectedHttpStatusCode(200);
        helloApiSteps.expectedJsonMessage(expectedMessage);
    }
}
