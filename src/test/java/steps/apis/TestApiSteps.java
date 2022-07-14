package steps.apis;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static steps.apis.CombinableMatcher.all;

public class TestApiSteps extends CommonApiSteps {

    private final String apiContext = "test/api-platform-test";
    private final String acceptHeaderV1_0 = jsonAcceptHeader("1.0");
    private final String acceptHeaderV2_0 = jsonAcceptHeader("2.0");
    private final String acceptHeaderV2_1 = jsonAcceptHeader("2.1");
    private final String acceptHeaderV2_2 = jsonAcceptHeader("2.2");
    private final String acceptHeaderV2_3 = jsonAcceptHeader("2.3");
    private final String acceptHeaderV3_0 = jsonAcceptHeader("3.0");
    private final String acceptHeaderV5_0 = jsonAcceptHeader("5.0");
    private final String acceptHeaderXmlV5_0 = xmlAcceptHeader("5.0");

    private String jsonAcceptHeader(String version) {
        return format("application/vnd.hmrc.%s+json", version);
    }

    private String xmlAcceptHeader(String version) {
        return format("application/vnd.hmrc.%s+xml", version);
    }

    private Payload payload;

    private CombinableMatcher<Payload> hasPayloadAttributesOf(Payload expect) {
        return all(Payload.hasMethodFeatureMatcher(expect.getMethod()))
                .and(Payload.hasUriFeatureMatcher(expect.getUri()))
                .and(Payload.hasResourceDetailsFeatureMatcher(expect.getResourceDetails()));
    }

    private void getOnUrl(String url, String acceptHeader) {
        builder().setAccept(acceptHeader);

        Response r =
                given()
                        .spec(specification())
                        .get(url);

        response(r.then());
        assertThat(format("Call to %s failed with status %d ", url, r.statusCode()), r.statusCode(), allOf(greaterThanOrEqualTo(200), lessThan(504)));
        payload = r.as(Payload.class);
    }

    private void getOnUrlV2_0(String url) {
        getOnUrl(url, acceptHeaderV2_0);
    }

    private void getOnUrlV2_1(String url) {
        getOnUrl(url, acceptHeaderV2_1);
    }

    private void getOnUrlV2_2(String url) {
        getOnUrl(url, acceptHeaderV2_2);
    }

    private void getOnUrlV3_0(String url) {
        getOnUrl(url, acceptHeaderV3_0);
    }

    @Step
    public void iMakeACallToHelloDave() {
        builder().setAccept(acceptHeaderV1_0);

        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/" + apiContext + "/hello/dave")
                        .then()
        );

    }

    @Step
    public void iMakeACallToNotifications() {
        builder().setAccept(acceptHeaderV1_0);

        response(
                given()
                        .spec(specification())
                        .post(baseApiUrl() + "/" + apiContext + "/notifications")
                        .then()

        );

    }

    @Step
    public void iMakeACallToDestinationNotifications() {
        builder().setAccept(acceptHeaderV1_0);

        response(
                given()
                        .spec(specification())
                        .post(baseApiUrl() + "/" + apiContext + "/destination/notifications?status=200&delayInSeconds=0")
                        .then()

        );

    }

    @Step
    public void iMakeACallToHelloBruce() {
        builder().setAccept(acceptHeaderV1_0);

        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/" + apiContext + "/hello/bruce")
                        .then()
        );

    }

    @Step
    public void iMakeACallToRootOnVersion2_0() {
        getOnUrlV2_0(baseApiUrl() + "/" + apiContext);
    }

    @Step
    public void iMakeACallToRootWithATrailingSlashOnVersion2_0() {
        getOnUrlV2_0(baseApiUrl() + "/" + apiContext  + "/");
    }

    @Step
    public void iMakeACallToRootOnVersion2_1() {
        getOnUrlV2_1(baseApiUrl() + "/" + apiContext + "/");
    }

    @Step
    public void iMakeACallToRootOnVersion2_2() {
        getOnUrlV2_2(baseApiUrl() + "/" + apiContext + "/");
    }

    @Step
    public void iMakeACallToCiao() {
        getOnUrlV2_0(baseApiUrl() + "/" + apiContext + "/ciao");
    }

    @Step
    public void iMakeACallToCiaoSurname(String surname) {
        getOnUrlV2_0(baseApiUrl() + "/" + apiContext + "/ciao/" + surname);
    }

    @Step
    public void iMakeACallToCiaoFullName(String surname, String firstname, String optionalMiddleName) {
        getOnUrlV2_0(baseApiUrl() + "/" + apiContext + "/ciao/" + surname + "/" + firstname + "?middleName=" + optionalMiddleName);
    }

    @Step
    public void iMakeACallToGetAddress(String city) {
        getOnUrlV3_0(baseApiUrl() + "/" + apiContext + "/city-details/" + city + "/" + "address");
    }

    @Step
    public void iMakeACallToPutAddress(String city) {
        builder().setAccept(acceptHeaderV3_0);

        Response r =
                given()
                        .spec(specification())
                        .formParam("parameter1", "value1")
                        .put(baseApiUrl() + "/" + apiContext + "/city-details/" + city + "/" + "address");
        response(r.then());
        assertThat(format("Call failed with status %d ", r.statusCode()), r.statusCode(), allOf(greaterThanOrEqualTo(200), lessThan(400)));
        payload = r.as(Payload.class);
    }

    @Step
    public void iMakeACallToGetPostcode(String city, String postcode) {
        getOnUrlV3_0(baseApiUrl() + "/" + apiContext + "/city-details/" + city + "/" + postcode);
    }

    @Step
    public void iMakeACallToPostPostcode(String city, String postcode) {
        builder().setAccept(acceptHeaderV3_0);

        Response r =
                given()
                        .spec(specification())
                        .formParam("parameter1", "value1")
                        .post(baseApiUrl() + "/" + apiContext + "/city-details/" + city + "/" + postcode);
        response(r.then());
        assertThat(format("Call failed with status %d ", r.statusCode()), r.statusCode(), allOf(greaterThanOrEqualTo(200), lessThan(400)));
        payload = r.as(Payload.class);
    }

    @Step
    public void iMakeACallToDeletePostcode(String city, String postcode) {
        builder().setAccept(acceptHeaderV3_0);

        Response r =
                given()
                        .spec(specification())
                        .delete(baseApiUrl() + "/" + apiContext + "/city-details/" + city + "/" + postcode);
        response(r.then());
        assertThat(format("Call failed with status %d ", r.statusCode()), r.statusCode(), allOf(greaterThanOrEqualTo(200), lessThan(400)));
        payload = r.as(Payload.class);
    }

    @Step
    public void iMakeACallToUnpublishedEndpoint() {
        builder().setAccept(acceptHeaderV2_0);

        response(
                given()
                        .spec(specification())
                        .get(baseApiUrl() + "/" + apiContext + "/ciao/kennedy/john/fitzgerald")
                        .then()
        );
    }

    @Step
    public void iMakeARequestToThePostJsonEndpoint() {
        builder().setAccept(acceptHeaderV5_0);

        response(
                given()
                        .spec(specification())
                        .post(baseApiUrl() + "/" + apiContext + "/json")
                        .then().log().all()
        );
    }

    @Step
    public void iMakeACallToPostXmlEndpoint() {
        builder().setAccept(acceptHeaderXmlV5_0);

        response(
                given()
                        .spec(specification())
                        .post(baseApiUrl() + "/" + apiContext + "/xml")
                        .then()
        );
    }

    @Step
    public void iMakeACallToPostRestrictedNrsEndpoint() {
        builder().setAccept(acceptHeaderV5_0);

        response(
                given()
                        .spec(specification())
                        .post(baseApiUrl() + "/" + apiContext + "/restricted-nrs")
                        .then()
        );
    }

    @Step
    public void iMakeACallToThePrivilegedEndpoint() {
        builder().setAccept(acceptHeaderV2_3);
        response(
                given()
                        .spec(specification())
                        .get(format("%s/%s/privileged", baseApiUrl(), apiContext))
                        .then());
    }

    @Step
    public void thePayloadIs(Payload expected) {
        assertThat(payload, hasPayloadAttributesOf(expected));
    }

    @Step
    public void theMethodIsGet() {
        assertThat(payload, Payload.hasMethodFeatureMatcher("GET"));
    }

    @Step
    public void theUrlMatches(String urlFragment) {
        assertThat(payload, Payload.hasUriFeatureMatcher(urlFragment));
    }

    @Step
    public void theResourceDetailsMatch(String details) {
        assertThat(payload, Payload.hasResourceDetailsFeatureMatcher(details));
    }

    @Step
    public void expectedJsonResponse() {
        commonResponseSteps.expectedContentType(JSON);
        response().body("Firstname", is("Joe"));
    }

    @Step
    public void expectedNotificationsJsonResponse() {
        commonResponseSteps.expectedContentType(JSON);
        response().body("boxId", is(notNullValue()));
        response().body("correlationId", is(notNullValue()));
    }

    @Step
    public void expectedXmlResponse() {
        commonResponseSteps.expectedContentType(XML);
        response().body(hasXPath("/Ping/Pong[text()='blah']"));
    }

    @Step
    public void expectedJsonMessage(String expectedMessage) {
        commonResponseSteps.expectedContentType(JSON);
        response().body("message", is(expectedMessage));
    }

    @Step
    public void hasUserCredentials() {
        response().body("credentials.providerId", is("0000001750989867"));
        response().body("credentials.providerType", is("GovernmentGateway"));
        response().body("name.name", is("TestUser"));
        response().body("email", is("api.platform@digital.hmrc.gov.uk"));
        response().body("affinityGroup", is("Individual"));
        response().body("groupIdentifier", containsString("testGroupId-"));
    }

    @Step
    public void hasAuthenticationHeaders(String accessToken) {
        response().body("headers.find { it.key == 'Host' }.value", is("api-platform-test.protected.mdtp"));
        response().body("headers.find { it.key == 'authorization' }.value", containsString("Bearer"));
        response().body("headers.find { it.key == 'x-client-id' }.value", is(config.clientId()));
        response().body("headers.find { it.key == 'x-application-id' }.value", is(config.applicationId()));
        response().body("headers.find { it.key == 'x-client-authorization-token' }.value", is(accessToken));
    }

    @Step
    public void hasCorrectClientDetails() {
        response().body("authProviderId.clientId", is(config.clientId()));
        response().body("clientId", is(config.clientId()));
        response().body("applicationName", is("API Platform Acceptance Tests"));
        response().body("applicationId", is(config.applicationId()));
    }

    @Step
    public void hasCorrectRopcClientDetails() {
        response().body("authProviderId.clientId", is(config.ropcClientId()));
        response().body("clientId", is(config.ropcClientId()));
        response().body("applicationName", is("API Platform ROPC Application"));
        response().body("applicationId", is("62e730a8-a728-49db-8ad1-2527165fb58d"));
    }

    @Step
    public void hasCorrectPrivilegedClientDetails() {
        response().body("authProviderId.clientId", is(config.privilegedClientId()));
        response().body("clientId", is(config.privilegedClientId()));
        response().body("applicationName", is("API Platform Privileged Application"));
        response().body("applicationId", is("b0dac28d-083f-4ca1-88f8-d8611d4d6473"));
    }

    public void iHaveAJsonPayload(String jsonText) {
        builder()
                .withJsonBody(jsonText);
    }

    public void iHaveAnXmlPayload(String xmlText) {
        builder()
                .withXmlBody(xmlText);
    }

    public void iHaveNoJsonPayload() {
        builder()
                .withJsonBody("");
    }

    public void iHaveNoXmlPayload() {
        builder()
                .withXmlBody("");
    }

    public void iHaveAJsonPayloadWithNoContentTypeHeader(String jsonText) {
        builder()
                .withNoContentTypeHeader(jsonText);
    }

    public void iHaveAXmlPayloadWithNoContentTypeHeader(String xmlText) {
        builder()
                .withNoContentTypeHeader(xmlText);
    }

    public void iShouldGetAJsonResponseWithHashPayloadOf(String expectedHash) {
        commonResponseSteps.expectedContentType(JSON);
        response().body("hash", equalTo(expectedHash));
    }
}
