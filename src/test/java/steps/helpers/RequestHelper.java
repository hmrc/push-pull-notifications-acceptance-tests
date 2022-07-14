package steps.helpers;

import components.HmrcRequestSpecBuilder;
import io.cucumber.java.Before;
import io.restassured.specification.RequestSpecification;

public class RequestHelper {

    private HmrcRequestSpecBuilder bldr = new HmrcRequestSpecBuilder();

    public HmrcRequestSpecBuilder builder() {
        return bldr;
    }

    public RequestSpecification specification() {
        return bldr.build();
    }

    public RequestSpecification specificationVersion(String version) {
        return bldr.build();
    }

    @Before
    public void cleanup() {
        bldr = new HmrcRequestSpecBuilder();
    }
}
