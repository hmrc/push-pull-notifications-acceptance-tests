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

    public String clientId() {
        return required("AUTH_CLIENT_ID");
    }

    public String clientSecret() {
        return required("AUTH_CLIENT_SECRET");
    }

    public String authRedirectUri() {
        return required("AUTH_REDIRECT_URI");
    }

    public String authServerToken() {
        return required("AUTH_SERVER_TOKEN");
    }

    public String callbackUrl() {
        return required("CALLBACK_URL");
    }

    public String cmbClientId() {
        return required("CMB_AUTH_CLIENT_ID");
    }

    public String cmbClientSecret() {
        return required("CMB_AUTH_CLIENT_SECRET");
    }


    public String blockedAuthServerToken() {
        return required("BLOCKED_AUTH_SERVER_TOKEN");
    }

    public int proxyPort() {
        return Integer.valueOf(required("PROXY_PORT"));
    }

}
