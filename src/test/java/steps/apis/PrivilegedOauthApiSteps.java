package steps.apis;

import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import steps.oauth.TotpGeneratorSteps;

public class PrivilegedOauthApiSteps extends AbstractOauthApiSteps {

    @Steps
    TotpGeneratorSteps totpGeneratorSteps;

    private final String goodPrivilegedClientId = config.privilegedClientId();
    private final String goodPrivilegedClientSecret = config.privilegedClientSecret();
    private final String privilegedClientIdWithMissingScopes = config.privilegedClientIdWithMissingScopes();
    private final String privilegedClientSecretWithMissingScopes = config.privilegedClientSecretWithMissingScopes();
    private final String privilegedClientIdWithNoScopes = config.privilegedClientIdWithNoScopes();
    private final String privilegedClientSecretWithNoScopes = config.privilegedClientSecretWithNoScopes();
    private final String privilegedClientIdWithNoSubscriptions = config.privilegedClientIdWithNoSubscriptions();
    private final String privilegedClientSecretWithNoSubscriptions = config.privilegedClientSecretWithNoSubscriptions();

    private String totpCode() {
        return totpGeneratorSteps.getTotpCode();
    }

    private RequestSpecification privilegedOauthRequestSpecification(String grantType, String clientId, String totpCode) {
        return oauthRequestSpecification(grantType,
                HashMap.of(
                        "client_id", clientId,
                        "client_secret", totpCode
                ));
    }

    private RequestSpecification privilegedOauthRequestSpecification(String clientId, String totpCode) {
        return privilegedOauthRequestSpecification("client_credentials", clientId, totpCode);
    }

    @Step
    public void successfullyGenerateAccessTokenForPrivilegedApp() {
        RequestSpecification spec = privilegedOauthRequestSpecification(goodPrivilegedClientId, totpCode() + goodPrivilegedClientSecret);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void successfullyGenerateAccessTokenForPrivilegedAppWithMissingScopes() {
        RequestSpecification spec = privilegedOauthRequestSpecification(privilegedClientIdWithMissingScopes, totpCode() + privilegedClientSecretWithMissingScopes);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void attemptToGenerateAccessTokenForPrivilegedAppWithNoScopes() {
        RequestSpecification spec = privilegedOauthRequestSpecification(privilegedClientIdWithNoScopes, totpCode() + privilegedClientSecretWithNoScopes);
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void attemptToGenerateAccessTokenForPrivilegedAppWithNoSubscriptions() {
        RequestSpecification spec = privilegedOauthRequestSpecification(privilegedClientIdWithNoSubscriptions, totpCode() + privilegedClientSecretWithNoSubscriptions);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void attemptToGenerateAccessTokenForPrivilegedApp() {
        RequestSpecification spec = privilegedOauthRequestSpecification(goodPrivilegedClientId, totpCode());
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void attemptToGenerateAccessTokenForPrivilegedAppWithInvalidClientId() {
        String invalidClientId = String.format("abc", goodPrivilegedClientId);
        RequestSpecification spec = privilegedOauthRequestSpecification(invalidClientId, totpCode());
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void attemptToGenerateAccessTokenForPrivilegedAppWithInvalidGrantType() {
        RequestSpecification spec = privilegedOauthRequestSpecification("INVALID_GRANT_TYPE", goodPrivilegedClientId, totpCode());
        callOauthTokenEndpoint(spec);
    }

    @Step
    public void assertLastOauthCallFailedDueToInvalidClientId() {
        assertLastOauthCallFailed(401, "invalid_client", "invalid client id or secret");
    }
}
