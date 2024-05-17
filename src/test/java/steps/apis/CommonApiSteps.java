package steps.apis;

import components.HmrcRequestSpecBuilder;
import configuration.HasConfiguration;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.Validate;
import steps.helpers.RequestHelper;

public class CommonApiSteps extends HasConfiguration {

    @Steps(shared = true)
    protected ResponseSteps responseSteps;
    @Steps(shared = true)
    private RequestHelper requestHelper;

    //
    // Helper methods to save indirection to steps
    //
    public void response(ValidatableResponse response) {
        responseSteps.response(response);
    }

    public ValidatableResponse response() {
        return Validate.notNull(responseSteps.response(), "The response is not assigned");
    }

    public RequestSpecification specification() {
        return requestHelper.specification();
    }

    public RequestSpecification specification(String version) {
        return requestHelper.specification();
    }

    public HmrcRequestSpecBuilder builder() {
        return requestHelper.builder();
    }
}
