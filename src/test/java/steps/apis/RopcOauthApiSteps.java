package steps.apis;

import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;

public class RopcOauthApiSteps extends AbstractOauthApiSteps {

    private final String goodRopcClientId = config.ropcClientId();
    private final String goodRopcClientSecret = config.ropcClientSecret();
    private final String goodRopcUsername = config.ropcUsername();
    private final String goodRopcPassword = config.ropcPassword();
    private final String standardApplicationClientId = config.clientId();
    private final String standardApplicationClientSecret = config.clientSecret();
    private final String privilegedApplicationClientId = config.privilegedClientId();
    private final String privilegedApplicationClientSecret = config.privilegedClientSecret();

    private final HashMap<String, String> goodParams = HashMap.of(
            "client_id", goodRopcClientId,
            "client_secret", goodRopcClientSecret,
            "username", goodRopcUsername,
            "password", goodRopcPassword
    );

    private final HashMap<String, String> standardApplicationParams = HashMap.of(
            "client_id", standardApplicationClientId,
            "client_secret", standardApplicationClientSecret,
            "username", goodRopcUsername,
            "password", goodRopcPassword
    );

    private final HashMap<String, String> privilegedApplicationParams = HashMap.of(
            "client_id", privilegedApplicationClientId,
            "client_secret", privilegedApplicationClientSecret,
            "username", goodRopcUsername,
            "password", goodRopcPassword
    );

    private final RequestSpecification ropcOauthRequestSpecification(HashMap<String, String> params) {
        return oauthRequestSpecification("password", params);
    }

    @Step
    public void generateAccessTokenForRopcApplication() {
        RequestSpecification spec = ropcOauthRequestSpecification(goodParams);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }


    @Step
    public void attemptToGenerateRopcTokenForStandardApplication() {
        RequestSpecification spec = ropcOauthRequestSpecification(standardApplicationParams);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallFailed(400, "unauthorized_client", "client id invalid for the password grant type");
    }

    @Step
    public void attemptToGenerateRopcTokenForPrivilegedApplication() {
        RequestSpecification spec = ropcOauthRequestSpecification(privilegedApplicationParams);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallFailed(400, "unauthorized_client", "client id invalid for the password grant type");
    }

    @Step
    public void attemptToGenerateAccessTokenForRopcApplicationWithUnsupportedGrantType() {
        RequestSpecification spec = oauthRequestSpecification("INVALID_GRANT_TYPE", goodParams);
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void attemptToGenerateAccessTokenForRopcApplicationWithBadUsername() {
        RequestSpecification spec = ropcOauthRequestSpecification(
            goodParams.replaceValue("username", "invalidUserName")
        );
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void attemptToGenerateAccessTokenForRopcApplicationWithBadPassword() {
        RequestSpecification spec = ropcOauthRequestSpecification(
            goodParams.replaceValue("password", "invalidPassword")
        );
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void assertLastOauthCallFailedDueToInvalidUsernameOrPassword() {
        assertLastOauthCallFailed(400, "invalid_grant", "invalid username or password");
    }
}
