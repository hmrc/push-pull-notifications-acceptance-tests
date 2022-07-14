package steps.apis;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class IndividualIncomeApiSteps extends CommonApiSteps {

    @Step
    public void iMakeARequestToTheGetIncomeSummaryEndpoint(String utr, String taxYear) {
        response(
                given()
                    .spec(specification())
                    .get(baseApiUrl() + "/individual-income/sa/" + utr + "/annual-summary/" + taxYear)
                .then()
        );
    }

    @Step
    public void iMakeARequestToTheGetIncomeSummaryRootEndpoint() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/individual-income")
                        .then()
        );
    }

    @Step
    public void iMakeARequestToTheGetIncomeSummaryRootEndpointWithATrailingSlash() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/individual-income/")
                        .then()
        );
    }

    @Step
    public void hasNoOfEmployments(int expectedNoOfEmployments) {
        response().body("employments.employerPayeReference", hasSize(2));
    }

    @Step
    public void hasPayeReferences(String... references) {
        response().body("employments.employerPayeReference", hasItems(references));
    }
}
