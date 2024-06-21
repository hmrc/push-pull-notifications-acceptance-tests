package apis.common;

import helpers.apis.HmrcRequestSpecBuilder;
import helpers.apis.RequestHelper;
import helpers.apis.ResponseHelper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.lang3.Validate;
import utilities.configuration.HasConfiguration;

public class CommonApi extends HasConfiguration {

    @Steps(shared = true)
    protected ResponseHelper responseHelper;
    @Steps(shared = true)
    private RequestHelper requestHelper;

    //
    // Helper methods to save indirection to old.steps
    //
    public void response(ValidatableResponse response) {
        responseHelper.response(response);
    }

    public ValidatableResponse response() {
        return Validate.notNull(responseHelper.response(), "The response is not assigned");
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
