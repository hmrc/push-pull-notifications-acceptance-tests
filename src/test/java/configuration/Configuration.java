package configuration;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.commons.lang3.Validate;

public class Configuration {

    private final EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();

    public String required(String envVar, String errorMessage) {
        return Validate.notNull(env.getValue(envVar), errorMessage);
    }

    public String required(String envVar) {
        return Validate.notNull(env.getValue(envVar), "Required env var for " + envVar);
    }

    public boolean isRunningOnCI() {
        try {
            // Force to false, this CI check and all the proxy code needs removing
            return false;
            // return Boolean.valueOf(env.getValue("CI", "false"));
        } catch (Exception e) {
            return false;
        }
    }

    public String baseUrl() {
        return required("BASE_URL", "Tests require a BASE_URL - e.g.https://www.qa.tax.service.gov.uk");
    }

    public String baseApiUrl() {
        return required("BASE_API_URL", "Tests require a BASE_API_URL to call APIs - e.g. https://api.qa.tax.service.gov.uk");
    }

    public String baseSandboxUrl() {
        return required("BASE_SANDBOX_URL", "Tests require a BASE_SANDBOX_URL - e.g. https://www.development.tax.service.gov.uk");
    }

    public String applicationId() {
        return required("APPLICATION_ID");
    }

    public String clientId() {
        return required("AUTH_CLIENT_ID");
    }

    public String openIdApplicationClientId() {
        return required("OPENID_APPLICATION_AUTH_CLIENT_ID");
    }

    public String blockedClientId() {
        return required("BLOCKED_CLIENT_ID");
    }

    public String SandboxClientId() {
        return required("AUTH_SANDBOX_CLIENT_ID");
    }

    public String suppressedClientId() {
        return required("SUPPRESSED_AUTH_CLIENT_ID");
    }

    public String clientSecret() {
        return required("AUTH_CLIENT_SECRET");
    }

    public String blockedClientSecret() {
        return required("BLOCKED_CLIENT_SECRET");
    }

    public String authRedirectUri() {
        return required("AUTH_REDIRECT_URI");
    }

    public String blockedAuthRedirectUri() {
        return required("BLOCKED_REDIRECT_URI");
    }

    public String authServerToken() {
        return required("AUTH_SERVER_TOKEN");
    }

    public String WhitelistedIPAuthServerToken() {
        return required("WHITELISTED_IP_AUTH_SERVER_TOKEN");
    }

    public String blockedAuthServerToken() {
        return required("BLOCKED_AUTH_SERVER_TOKEN");
    }

    private String convertUserId(String userId) {
        return userId.toUpperCase().replaceAll("-", "_");
    }

    public String ropcClientId() {
        return required("ROPC_AUTH_CLIENT_ID");
    }

    public String ropcClientSecret() {
        return required("ROPC_AUTH_CLIENT_SECRET");
    }

    public String ropcUsername() {
        return required("ROPC_AUTH_USERNAME");
    }

    public String ropcPassword() {
        return required("ROPC_AUTH_PASSWORD");
    }

    public String privilegedClientId() {
        return required("PRIVILEGED_APPLICATION_CLIENT_ID");
    }

    public String privilegedClientIdWithMissingScopes() {
        return required("PRIVILEGED_APPLICATION_CLIENT_ID_MISSING_SCOPES");
    }

    public String privilegedClientIdWithNoScopes() {
        return required("PRIVILEGED_APPLICATION_CLIENT_ID_NO_SCOPES");
    }

    public String privilegedClientIdWithNoSubscriptions() {
        return required("PRIVILEGED_APPLICATION_CLIENT_ID_NO_SUBSCRIPTIONS");
    }

    public String privilegedClientSecret() {
        return required("PRIVILEGED_APPLICATION_CLIENT_SECRET");
    }

    public String privilegedClientSecretWithMissingScopes() {
        return required("PRIVILEGED_APPLICATION_CLIENT_SECRET_MISSING_SCOPES");
    }

    public String privilegedClientSecretWithNoScopes() {
        return required("PRIVILEGED_APPLICATION_CLIENT_SECRET_NO_SCOPES");
    }

    public String privilegedClientSecretWithNoSubscriptions() {
        return required("PRIVILEGED_APPLICATION_CLIENT_SECRET_NO_SUBSCRIPTIONS");
    }

    public String privilegedTotpSecret() {
        return required("PRIVILEGED_APPLICATION_TOTP_SECRET");
    }

    public String privilegedTotpSecretWithMissingScopes() {
        return required("PRIVILEGED_APPLICATION_TOTP_SECRET_MISSING_SCOPES");
    }

    public String privilegedTotpSecretWithNoScopes() {
        return required("PRIVILEGED_APPLICATION_TOTP_SECRET_NO_SCOPES");
    }

    public String privilegedTotpSecretWithNoSubscriptions() {
        return required("PRIVILEGED_APPLICATION_TOTP_SECRET_NO_SUBSCRIPTIONS");
    }

    public int proxyPort() {
        return Integer.valueOf(required("PROXY_PORT"));
    }

    public String publishingKey() {
        return required("PUBLISHING_KEY");
    }
}
