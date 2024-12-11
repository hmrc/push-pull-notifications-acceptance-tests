package apis;

import apis.common.CommonApi;
import helpers.payloads.BoxPayload;
import helpers.payloads.InvalidBoxPayload;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.annotations.Step;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsIn.oneOf;

public class PushPullNotificationsCommonApi extends CommonApi {

    //private static final String BASE_URL = "http://localhost:6701";
    private static final String BASE_URL = "https://push-pull-notifications-api.protected.mdtp";
    private static final String PUSH_PULL_BOX_URL = format("%s/box", BASE_URL);
    private static final String PUSH_PULL_CALLBACK_URL = "%s/box/%s/callback";
    private static final String PUSH_PULL_CALLBACK_NO_BOX_URL = format("%s/box/046ceee5-e43f-4159-b5ce-8df5f2b9d999/callback", BASE_URL);
    private static final String PUSH_PULL_NOTIFICATIONS_URL = "%s/box/%s/notifications";
    private static final String PUSH_PULL_NOTIFICATIONS_NO_BOX_URL = format("%s/box/07787f13-dcae-4168-8685-c00a33b86999/notifications", BASE_URL);
    private static final String PUSH_PULL_NOTIFICATIONS_URL_INVALID_UUID = format("%s/box/foobar/notifications", BASE_URL);
    private static final String PUSH_PULL_WRAPPED_NOTIFICATIONS_URL = "%s/box/%s/wrapped-notifications";
    private static final String PUSH_PULL_WRAPPED_NOTIFICATIONS_NO_BOX_URL = format("%s/box/07787f13-dcae-4168-8685-c00a33b86999/wrapped-notifications", BASE_URL);
    private static final String PUSH_PULL_WRAPPED_NOTIFICATIONS_URL_INVALID_UUID = format("%s/box/foobar/wrapped-notifications", BASE_URL);
    private static final String PUSH_PULL_SECRETS_URL = "%s/client/%s/secrets";
    private static final String PUSH_PULL_CREATE_CMB_BOX_URL = format("%s/cmb/box", BASE_URL);
    private final String apiContext = "misc/push-pull-notification/box";
    private String authorizationKey;
    private String userAgent;
    private String internalBearerToken;
    private String clientId;
    private String boxName;
    private String newBoxId;
    private String newClientManagedBoxId;
    private String notificationId;
    private final String newBoxName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());

    public String getNewBoxName() {
        return newBoxName;
    }

    public String getNewBoxId() {
        return newBoxId;
    }

    @Step
    public void iMakeACallToCreateBoxWithPayload(String clientId, String boxName) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(new BoxPayload(clientId, boxName));

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(PUSH_PULL_BOX_URL).then());
    }

    @Step
    public void iMakeACallToCreateBoxWithInvalidBody(String clientId, String boxName) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(new InvalidBoxPayload(clientId, boxName));

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(PUSH_PULL_BOX_URL).then());
    }

    @Step
    public void iMakeACallToCreateBoxWithNoBody() {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification());

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(PUSH_PULL_BOX_URL).then());
    }

    @Step
    public void iMakeACallToCallbackWithPayload(String boxId, String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(format(PUSH_PULL_CALLBACK_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallTCallbackWithNoPayload(String boxId) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification());

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(format(PUSH_PULL_CALLBACK_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallToCallbackWhereNoBoxExists(String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.put(PUSH_PULL_CALLBACK_NO_BOX_URL).then());
    }

    @Step
    public void iMakeACallToCreateNotificationsWithJsonPayload(String boxId, String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(format(PUSH_PULL_NOTIFICATIONS_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallToCreateNotificationsWithJsonPayloadFile(String boxId) {
        builder().withNoProxy();

        String location = "notifications/2Mb.json";
        File jsonDataPayload = new File(location);

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonDataPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(format(PUSH_PULL_NOTIFICATIONS_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallToCreateNotificationsWithJsonPayloadFileTooLarge(String boxId) {
        builder().withNoProxy();

        //notifications.maxSize configured to 2.1 MB in QA
        String location = "notifications/3Mb.json";
        File jsonDataPayload = new File(location);

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonDataPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(format(PUSH_PULL_NOTIFICATIONS_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallToCreateNotificationsWithXmlPayload(String boxId, String xmlPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(xmlPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(format(PUSH_PULL_NOTIFICATIONS_URL, BASE_URL, boxId)).then());
    }

    @Step
    public void iMakeACallToCreateNotificationsWhereNoBoxExists(String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(PUSH_PULL_NOTIFICATIONS_NO_BOX_URL).then());
    }


    @Step
    public void iMakeACallToCreateNotificationsWithInvalidUuid(String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(PUSH_PULL_NOTIFICATIONS_URL_INVALID_UUID).then());
    }

    @Step
    public void iMakeACallToCreateWrappedNotificationsWithJsonPayload(String boxId, String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(format(PUSH_PULL_WRAPPED_NOTIFICATIONS_URL, BASE_URL, boxId)).then());
    }

    public void iMakeACallToCreateWrappedNotificationsWhereNoBoxExists(String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(PUSH_PULL_WRAPPED_NOTIFICATIONS_NO_BOX_URL).then());
    }

    @Step
    public void iMakeACallToCreateWrappedNotificationsWithInvalidUuid(String jsonPayload) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post(PUSH_PULL_WRAPPED_NOTIFICATIONS_URL_INVALID_UUID).then());
    }

    @Step
    public void iMakeACallToCreateBoxWithNoQueryParameters() {
        builder().withNoProxy();

        Response r =
                given()
                        .spec(specification())
                        .get(PUSH_PULL_BOX_URL);

        response(r.then());
    }

    @Step
    public void iMakeACallToCreateBoxWithQueryParameters(String clientIdParameterName, String clientIdValue, String boxNameParameterName, String boxNameValue) {
        builder().withNoProxy();

        Response r =
                given()
                        .spec(specification())
                        .get(PUSH_PULL_BOX_URL + "?" + clientIdParameterName + "=" + clientIdValue + "&" + boxNameParameterName + "=" + boxNameValue);

        response(r.then());
    }

    @Step
    public void iMakeACallToSecrets(String clientId) {
        builder().withNoProxy();

        RequestSpecification spec = given()
                .spec(specification());

        if (authorizationKey != null) {
            spec = spec.header("Authorization", authorizationKey);
        }

        response(spec.get(format(PUSH_PULL_SECRETS_URL, BASE_URL, clientId)).then());
    }

    @Step
    public void withAuthorizationKey(final String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    @Step
    public void withUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }

    @Step
    public void withInternalBearerToken(final String internalBearerToken) {
        this.internalBearerToken = internalBearerToken;
    }

    @Step
    public void withValidQueryParameters(final String clientId, String boxName) {
        this.clientId = clientId;
        this.boxName = boxName;
    }

    @Step
    public void assertCallbackUrlUpdated() {
        response().body("successful", is(true));
    }

    @Step
    public void assertNotificationCreated() {
        response().body("notificationId", is(notNullValue()));
        notificationId = response().extract().path("notificationId").toString();
    }

    @Step
    public void assertNotificationWithConfirmationUrlCreated() {
        response().body("notificationId", is(notNullValue()));
        response().body("confirmationId", is(notNullValue()));
        notificationId = response().extract().path("notificationId").toString();
    }

    @Step
    public void assertBoxGenerated() {
        response().body("boxId", is("de9aed9c-b319-49a4-99e9-a8a659fc0bf6"));
    }

    @Step
    public void assertNewBoxGenerated() {
        newBoxId = response().extract().path("boxId").toString();
    }

    @Step
    public void asserValidateClientManagedBoxResponse(Boolean validValue) {
        response().body("valid", is(validValue));
    }

    @Step
    public void assertValidateCallbackUrlResponse(Boolean value) {
        response().body("successful", is(value));
    }

    @Step
    public void assertInvalidCallbackUrlResponse(Boolean value) {
        assertValidateCallbackUrlResponse(value);
        response().body("errorMessage", is("Invalid callback URL. Check the information you have provided is correct."));
    }

    @Step
    public void assertBoxGeneratedWithNoPayload() {
        response().body("boxId", is("ef9aebe1-24d3-4c41-9b22-e1eeedb32d45"));
    }

    @Step
    public void assertAllBoxes() {
        response().body("boxId", everyItem(is(notNullValue())));
        response().body("boxName", everyItem(is(notNullValue())));
        response().body("boxCreator", everyItem(is(notNullValue())));
    }

    @Step
    public void assertBoxExists() {
        response().body("boxId", is("5fc1f8e5-8881-4863-8a8c-5c897bb56815"));
        response().body("boxName", is("API Platform Acceptance Test Box"));
        response().body("boxCreator.clientId", is("3ZdSQUrCrLEoyXFRjCgmj60qlfAa"));
    }

    @Step
    public void assertNewBoxExists() {
        response().body("boxId", is(newBoxId));
        response().body("boxName", is(newBoxName));
        response().body("boxCreator.clientId", is(config.clientId()));
    }

    @Step
    public void iMakeACallToTheExternalGetBoxNotifications(String boxId) {

        response(
                given()
                        .spec(specification())
                        .get(format("%s/%s/%s/notifications", baseApiUrl(), apiContext, boxId))
                        .then()
        );
    }

    @Step
    public void iMakeACallToTheExternalGetBoxNotificationsWithQueryParameters(String boxId, String statusQueryParam, String statusQueryValue, String fromDateQueryParam, String fromDateQueryValue, String toDateQueryParam, String toDateQueryValue) {

        response(
                given()
                        .spec(specification())
                        .get(format("%s/%s/%s/notifications?%s=%s&%s=%s&%s=%s", baseApiUrl(), apiContext, boxId, statusQueryParam, statusQueryValue, fromDateQueryParam, fromDateQueryValue, toDateQueryParam, toDateQueryValue))
                        .then()
        );
    }

    @Step
    public void iMakeACallToTheExternalGetBoxNotifications(String boxId, String statusQueryParam, String statusQueryValue) {

        response(
                given()
                        .spec(specification())
                        .get(format("%s/%s/%s/notifications", baseApiUrl(), apiContext, boxId))
                        .then()
        );
    }

    @Step
    public void iMakeACallToTheExternalGetBoxNotificationsWithOnlyStatusQueryParameter(String boxId, String statusQueryParam, String statusQueryValue) {

        response(
                given()
                        .spec(specification())
                        .get(format("%s/%s/%s/notifications?%s=%s", baseApiUrl(), apiContext, boxId, statusQueryParam, statusQueryValue))
                        .then()
        );
    }

    @Step
    public void iMakeACallToTheExternalPutAcknowledgeNotifications(String boxId) {

        response(
                given()
                        .spec(specification())
                        .body(format("{\"notificationIds\": [\"%s\"]}", notificationId))
                        .put(format("%s/%s/%s/notifications/acknowledge", baseApiUrl(), apiContext, boxId))
                        .then()
        );
    }

    @Step
    public void hasCorrectNotificationDetailsForTheNewBox() {
        response().body("notificationId", is(singletonList(notificationId)));
        response().body("boxId", is(singletonList(newBoxId)));
        response().body("message", is(singletonList("{\"message\" : \"jsonbody\"}")));
        response().body("status", is(singletonList("ACKNOWLEDGED")));
        response().body("createdDateTime", is(notNullValue()));
    }

    @Step
    public void hasCorrectMaxSizeNotificationDetailsForTheNewBox() {
        response().body("notificationId", is(singletonList(notificationId)));
        response().body("boxId", is(singletonList(newBoxId)));
        response().body("message", is(notNullValue()));
        response().body("status", is(singletonList("ACKNOWLEDGED")));
        response().body("createdDateTime", is(notNullValue()));
    }

    @Step
    public void hasPendingStatusNotifications() {
        response().body("notificationId", hasItem((notificationId)));
        response().body("status", everyItem(is("PENDING")));
    }

    @Step
    public void hasCorrectNotificationDetailsForPendingStatusAndDateParameters() {
        response().body("notificationId", is(singletonList(notificationId)));
        response().body("boxId", is(singletonList(newBoxId)));
        response().body("message", is(singletonList("{\"message\" : \"jsonbody\"}")));
        response().body("status", is(singletonList("PENDING")));
        response().body("createdDateTime", is(notNullValue()));
    }

    @Step
    public void hasCorrectNotificationDetailsForAcknowledgedStatusAndDateParameters() {
        response().body("notificationId", is(singletonList(notificationId)));
        response().body("boxId", is(singletonList(newBoxId)));
        response().body("message", is(singletonList("{\"message\" : \"jsonbody\"}")));
        response().body("status", is(singletonList("ACKNOWLEDGED")));
        response().body("createdDateTime", is(notNullValue()));
    }

    public void hasAcknowledgedStatusNotifications() {
        response().body("notificationId", hasItems((notificationId)));
        response().body("status", everyItem(is("ACKNOWLEDGED")));
    }
}
