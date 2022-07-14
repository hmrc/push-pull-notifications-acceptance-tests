package steps.apis;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class IndividualBenefitsApiSteps extends CommonApiSteps {

    @Step
    public void iMakeARequestToTheGetBenefitsSummaryEndpoint(String utr, String taxYear) {
        response(
                given()
                    .spec(specification())
                    .get(baseApiUrl() + "/individual-benefits/sa/" + utr + "/annual-summary/" + taxYear)
                .then()
        );
    }

    @Step
    public void iMakeARequestToTheGetBenefitsSummaryRootEndpoint() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/individual-benefits")
                        .then()
        );
    }

    @Step
    public void iMakeARequestToTheGetBenefitsSummaryRootEndpointWithATrailingSlash() {
        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/individual-benefits/")
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
