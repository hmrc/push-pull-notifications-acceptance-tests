package steps.apis;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.Method.GET;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

public class MultiSegmentContextApiSteps extends CommonApiSteps {

    private static final String API_CONTEXT = "ciao/hey/welcome";

    private String jsonAcceptHeader(final String version) {
        return format("application/vnd.hmrc.%s+json", version);
    }

    private void getOnUrl(final String url, final String version) {
        requestOnUrl(url, GET.name(), version);
    }

    private void requestOnUrl(final String url, final String method, final String version) {
        builder().setAccept(jsonAcceptHeader(version));
        final Response r = given().spec(specification()).request(method, url);
        response(r.then());

    }

    @Step
    public void iMakeAGetRequestToRootOnVersion(final String version) {
        getOnUrl(format("%s/%s", baseApiUrl(), API_CONTEXT), version);
    }

    @Step
    public void iMakeAGetRequestToFriendOnVersion(final String friend, final String version) {
        getOnUrl(format("%s/%s/%s", baseApiUrl(), API_CONTEXT, friend), version);
    }

    @Step
    public void iMakeAGetRequestToFriendCityOnVersion(final String friend, final String city, final String version) {
        getOnUrl(format("%s/%s/%s/%s", baseApiUrl(), API_CONTEXT, friend, city), version);
    }

    @Step
    public void iMakeARequestToCountryDetailsOnVersion(final String country, final String method, final String version) {
        requestOnUrl(format("%s/%s/country-details/%s/display", baseApiUrl(), API_CONTEXT, country), method, version);
    }

    @Step
    public void iMakeARequestToCountryAndCityDetailsOnVersion(final String country, String city, final String method, final String version) {
        requestOnUrl(format("%s/%s/country-details/%s/%s", baseApiUrl(), API_CONTEXT, country, city), method, version);
    }

    @Step
    public void expectedJsonCode(final String expectedCode) {
        commonResponseSteps.expectedContentType(JSON);
        response().body("code", is(expectedCode));
    }

    @Step
    public void expectedJsonMessage(final String expectedMessage) {
        commonResponseSteps.expectedContentType(JSON);
        response().body("message", is(expectedMessage));
    }

    @Step
    public void expectedStatus(final int expectedStatus) {
        commonResponseSteps.expectedHttpStatusCode(expectedStatus);
    }
}
