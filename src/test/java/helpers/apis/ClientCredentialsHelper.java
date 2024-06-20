package helpers.apis;

import helpers.apis.AbstractOauthHelper;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.annotations.Step;

import java.util.HashMap;

public class ClientCredentialsHelper extends AbstractOauthHelper {

    private final String standardApplicationClientId = config.clientId();
    private final String standardApplicationClientSecret = config.clientSecret();
    private final String cmbApplicationClientId = config.cmbClientId();
    private final String cmbApplicationClientSecret = config.cmbClientSecret();
    private final String noBoxesApplicationClientId = config.noBoxesClientId();
    private final String noBoxesApplicationClientSecret = config.noBoxesClientSecret();

    private RequestSpecification standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(String grantType, String clientId, String clientSecret, String scope) {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("client_id", clientId);
        credentials.put("client_secret", clientSecret);
        credentials.put("scope", scope);
        return oauthRequestSpecification(grantType, credentials);
    }

    private RequestSpecification standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(String clientId, String clientSecret, String scope) {
        return standardOauthRequestSpecificationForGivenScopeUsingClientCredentials("client_credentials", clientId, clientSecret, scope);
    }

    @Step
    public void successfullyGenerateAccessTokenForGivenScopeForStandardAppUsingClientCredentials(String scope) {
        RequestSpecification spec = standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(standardApplicationClientId, standardApplicationClientSecret, scope);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void successfullyGenerateAccessTokenForGivenScopeForCmbAppUsingClientCredentials(String scope) {
        RequestSpecification spec = standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(cmbApplicationClientId, cmbApplicationClientSecret, scope);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void successfullyGenerateAccessTokenForGivenScopeForNoBoxesAppUsingClientCredentials(String scope) {
        RequestSpecification spec = standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(noBoxesApplicationClientId, noBoxesApplicationClientSecret, scope);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }
}
