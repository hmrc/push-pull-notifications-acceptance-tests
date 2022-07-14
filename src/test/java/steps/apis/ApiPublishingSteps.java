package steps.apis;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.joda.time.DateTime;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class ApiPublishingSteps extends CommonApiSteps {

    private static final String API_DEFINITION_URL = "https://api-definition.protected.mdtp/api-definition/%s";
    private static final String API_PUBLISHER_URL = "https://api-publisher.protected.mdtp/publish";
    private static final String PUBLISHING_KEY = Base64.getEncoder().encodeToString(config.publishingKey().getBytes());

    @Step
    public DateTime iMakeACallToFetchApiDefinitionFor(final String serviceName) {
        final String url = format(API_DEFINITION_URL, serviceName);
        builder().withNoProxy();
        Response r =
                given()
                        .spec(specification())
                        .get(url)
                ;

        response(r.then());
        assertThat(format("Call to %s failed with status %d ", url, r.statusCode()), r.statusCode(), allOf(greaterThanOrEqualTo(200), lessThan(412)));
        final String lastPublishedAt = r.getBody().jsonPath().getString("lastPublishedAt");
        return DateTime.parse(lastPublishedAt);
    }

    @Step
    public void iMakeACallToPublishTheApiDefinition(final ServiceLocatorPayload requestPayload) {
        builder().withNoProxy();
        Response r =
                given()
                        .spec(specification())
                        .header("Authorization", PUBLISHING_KEY)
                        .contentType(JSON)
                        .body(requestPayload)
                        .post(API_PUBLISHER_URL)
                ;

        response(r.then());
        assertThat(format("Call to %s failed with status %d ", API_PUBLISHER_URL, r.statusCode()), r.statusCode(), is(204));
    }

}
