package authloginstub;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("/oauth/start")
@At("https://.*.//oauth/start")
public class StartAuthorityPage extends BasePage {

    public String pageTitle = "HMRC Tax Platform";

    @FindBy(id = "authorise")
    WebElement grantAuthorityButton;

    @FindBy(linkText = "Continue")
    WebElement continueButton;

    public void continueToGrantAuthority() {
        continueButton.click();
    }

    public LocalhostRedirectPage submit() {
        grantAuthorityButton.click();
        return new LocalhostRedirectPage();
    }

    public void navigateToAuthoriseUrl(String scope) {
        getDriver().navigate().to(String.format("%s/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", config.baseUrl(), config.clientId(), config.authRedirectUri(), scope ));
    }

    public void navigateToAuthoriseUrlForOpenIdApplication(String scope) {
        getDriver().navigate().to(String.format("%s/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", config.baseUrl(), config.openIdApplicationClientId(), config.authRedirectUri(), scope ));
    }

    public void navigateToSandboxAuthoriseUrl(String scope) {
        getDriver().navigate().to(String.format("%s/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", config.baseSandboxUrl(), config.SandboxClientId(), config.authRedirectUri(), scope ));
    }


    public void navigateToRedirectUrlWithSuppressedApplication(String scope) {
        getDriver().navigate().to(String.format("%s/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", config.baseUrl(), config.suppressedClientId(), config.authRedirectUri(), scope ));
    }
}
