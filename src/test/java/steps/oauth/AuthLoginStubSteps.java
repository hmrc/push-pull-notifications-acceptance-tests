package steps.oauth;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import authloginstub.AuthLoginStubPage;
import authloginstub.GrantAuthorityPage;
import authloginstub.LocalhostRedirectPage;
import authloginstub.StartAuthorityPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthLoginStubSteps extends ScenarioSteps {

    private AuthLoginStubPage authLoginStubPage;
    private StartAuthorityPage startAuthorityPage;
    private GrantAuthorityPage grantAuthorityPage;
    private LocalhostRedirectPage localhostRedirectPage;

    @Step
    public void completeAuthStubForm(String scope) {
        openAuthLoginStubPage();
        completeTheAuthLoginStubForm(scope);
    }

    @Step
    public void openAuthLoginStubPage() {
        authLoginStubPage.open();
        assertThat(authLoginStubPage.getPageTitle(), is(authLoginStubPage.pageTitle));
    }

    @Step
    public void completeTheAuthLoginStubForm(String scope){
        authLoginStubPage.fillCredId();
        authLoginStubPage.fillRedirectUrl(scope);
        authLoginStubPage.setConfidenceLevel(250);
        authLoginStubPage.selectCredentialStrength("strong");
        authLoginStubPage.addNino();
        authLoginStubPage.addSaUtr();
        authLoginStubPage.submit();
        assertThat(startAuthorityPage.getPageTitle(), is(startAuthorityPage.pageTitle));
    }

    public void grantAuthority()   {
        startAuthorityPage.continueToGrantAuthority();
        assertThat(grantAuthorityPage.getPageTitle(), is(grantAuthorityPage.pageTitle));
        assertThat(grantAuthorityPage.getApplicationName(), is("API Platform Acceptance Tests"));
        grantAuthorityPage.submit();
    }


    public void grantAuthorityFromIv()   {
        assertThat(grantAuthorityPage.getPageTitle(), is(grantAuthorityPage.pageTitle));
        assertThat(grantAuthorityPage.getApplicationName(), is("API Platform Acceptance Tests"));
        grantAuthorityPage.submit();
    }

    @Step
    public void anAccessCodeIsSuccessfullyGenerated() {
        assertThat(localhostRedirectPage.getPageTitle(), is(localhostRedirectPage.pageTitle));
        localhostRedirectPage.getUrlAccessCode();
    }

    public void setAgentServicesDetails(String scope, String agentReferanceNumber)   {
        authLoginStubPage.fillRedirectUrl(scope);
        authLoginStubPage.selectCredentialStrength("strong");
        authLoginStubPage.selectAffinityGroup("Agent");
        authLoginStubPage.enterEnrolmentKey("HMRC-AS-AGENT");
        authLoginStubPage.identifierName("AgentReferenceNumber");
        authLoginStubPage.identifierValue(agentReferanceNumber);
        authLoginStubPage.submit();
        assertThat(startAuthorityPage.getPageTitle(), is(startAuthorityPage.pageTitle));
    }
}
