package steps.apis;

import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class PushPullNotificationsGatewaySteps extends CommonApiSteps {

    private String authorizationKey;
    private String userAgent;

    //private static final String PUSH_PULL_GATEWAY_NOTIFY_URL = "http://localhost:6702";
    private static final String PUSH_PULL_GATEWAY_NOTIFY_URL = "https://push-pull-notifications-gateway.public.mdtp";

    public void iMakeACallToNotifyWithPayload(String jsonPayload) {
        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (authorizationKey != null) {
            spec = spec.header("Authorization", authorizationKey);
        }

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post((PUSH_PULL_GATEWAY_NOTIFY_URL + "/notify")).then());
    }

    public void iMakeACallToNotifyWithNoPayload() {
        RequestSpecification spec = given()
                .spec(specification());

        if (authorizationKey != null) {
            spec = spec.header("Authorization", authorizationKey);
        }

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post((PUSH_PULL_GATEWAY_NOTIFY_URL + "/notify" )).then());
    }

    public void iMakeACallToValidateCallbackUrlWithPayload(String jsonPayload) {
        RequestSpecification spec = given()
                .spec(specification())
                .body(jsonPayload);

        if (authorizationKey != null) {
            spec = spec.header("Authorization", authorizationKey);
        }

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post((PUSH_PULL_GATEWAY_NOTIFY_URL + "/validate-callback")).then());
    }

    public void iMakeACallToValidateCallbackUrlWithNoPayload() {
        RequestSpecification spec = given()
                .spec(specification());

        if (authorizationKey != null) {
            spec = spec.header("Authorization", authorizationKey);
        }

        if (userAgent != null) {
            spec = spec.header("User-Agent", userAgent);
        }

        response(spec.post((PUSH_PULL_GATEWAY_NOTIFY_URL + "/validate-callback" )).then());
    }

    @Step
    public void withAuthorizationKey(final String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    @Step
    public void withUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }
}
