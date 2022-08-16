package steps.oauth;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;
import steps.oauth.AbstractOauthApiSteps;

public class ClientCredentialsApiSteps extends AbstractOauthApiSteps {

    private final String standardApplicationClientId = config.clientId();
    private final String standardApplicationClientSecret = config.clientSecret();

    private RequestSpecification standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(String grantType, String clientId, String clientSecret, String scope) {
        return oauthRequestSpecification(grantType,
                HashMap.of(
                        "client_id", clientId,
                        "client_secret", clientSecret,
                        "scope", scope
                ));
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
}
