package utilities.configuration;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class Configuration {

    final static Properties testData = loadTestData(getSerenityEnvironmentProperty("hmrc.envName").orElseThrow());

    public static Properties loadTestData(String envName) {
        Properties testData = new Properties();
        try {
            InputStream testDataFile = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(String.format("environmentTestData/%s/testData.properties", envName))).openStream();
            testData.load(testDataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testData;
    }

    public static Optional<String> getSerenityEnvironmentProperty(String property) {
        return EnvironmentSpecificConfiguration.from(Serenity.environmentVariables()).getOptionalProperty(property);
    }

    public String required(String envVar, String errorMessage) {
        return Validate.notNull(testData.getProperty(envVar), errorMessage);
    }

    public String required(String envVar) {
        return Validate.notNull(testData.getProperty(envVar), "Required env var for " + envVar);
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
        return required("baseUrl", "tests require a baseUrl - e.g.https://www.qa.tax.service.gov.uk");
    }

    public String baseApiUrl() {
        return required("baseApiUrl", "tests require a baseApiUrl to call apis - e.g. https://api.qa.tax.service.gov.uk");
    }

    public String clientId() {
        return required("authClientId");
    }

    public String clientSecret() {
        return required("authClientSecret");
    }

    public String authRedirectUri() {
        return required("authRedirectUri");
    }

    public String authServerToken() {
        return required("authServerToken");
    }

    public String callbackUrl() {
        return required("callbackUrl");
    }

    public String cmbClientId() {
        return required("cmbAuthClientId");
    }

    public String cmbClientSecret() {
        return required("cmbAuthClientSecret");
    }

    public String noBoxesClientId() {
        return required("noBoxesAuthClientId");
    }

    public String noBoxesClientSecret() {
        return required("noBoxesAuthClientSecret");
    }


    public String blockedAuthServerToken() {
        return required("blockedAuthServerToken");
    }

    public int proxyPort() {
        return Integer.valueOf(required("proxyPort"));
    }

}
