package steps.apis;

import io.restassured.specification.RequestSpecification;
import io.vavr.collection.HashMap;
import net.thucydides.core.annotations.Step;

public class ClientCredentialsApiSteps extends AbstractOauthApiSteps {

    private final String standardApplicationClientId = config.clientId();
    private final String standardApplicationClientSecret = config.clientSecret();
    private final String ropcApplicationClientId = config.ropcClientId();
    private final String ropcApplicationClientSecret = config.ropcClientSecret();


    private RequestSpecification standardOauthRequestSpecificationForDefaultScopeUsingClientCredentials(String grantType, String clientId, String clientSecret) {
        return oauthRequestSpecification(grantType,
                HashMap.of(
                        "client_id", clientId,
                        "client_secret", clientSecret
                ));
    }

    private RequestSpecification standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(String grantType, String clientId, String clientSecret, String scope) {
        return oauthRequestSpecification(grantType,
                HashMap.of(
                        "client_id", clientId,
                        "client_secret", clientSecret,
                        "scope", scope
                ));
    }

    private RequestSpecification standardOauthRequestSpecificatioForDefaultScopeUsingClientCredentials(String clientId, String clientSecret) {
        return standardOauthRequestSpecificationForDefaultScopeUsingClientCredentials("client_credentials", clientId, clientSecret);
    }

    private RequestSpecification standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(String clientId, String clientSecret, String scope) {
        return standardOauthRequestSpecificationForGivenScopeUsingClientCredentials("client_credentials", clientId, clientSecret, scope);
    }


    @Step
    public void successfullyGenerateAccessTokenWithDefaultScopeForAStandardAppUsingClientCredentials() {
        RequestSpecification spec = standardOauthRequestSpecificatioForDefaultScopeUsingClientCredentials(standardApplicationClientId, standardApplicationClientSecret);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void successfullyGenerateAccessTokenForGivenScopeForStandardAppUsingClientCredentials(String scope) {
        RequestSpecification spec = standardOauthRequestSpecificationForGivenScopeUsingClientCredentials(standardApplicationClientId, standardApplicationClientSecret, scope);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }

    @Step
    public void successfullyGenerateAccessTokenForAnRopcAppUsingClientCredentials() {
        RequestSpecification spec = standardOauthRequestSpecificatioForDefaultScopeUsingClientCredentials(ropcApplicationClientId, ropcApplicationClientSecret);
        callOauthTokenEndpoint(spec);
        assertLastOauthCallSucceeded();
        extractToken(lastOauthResponse);
    }
}
