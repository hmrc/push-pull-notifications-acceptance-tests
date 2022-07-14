package steps.apis;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class MarriageAllowanceApiSteps extends CommonApiSteps {

    @Step
    public void iMakeARequestToTheGetMarriageAllowanceStatus(String utr) {
        response(
                given()
                    .spec(specification())
                        .with().queryParam("taxYear", "2018-19")
                    .get(baseApiUrl() + "/marriage-allowance/sa/{utr}/status", utr)

                .then()
        );
    }
}
