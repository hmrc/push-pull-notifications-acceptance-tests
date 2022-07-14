package authloginstub;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("/oauth/grantscope")
@At("https://.*.//oauth/grantscope")
public class GrantAuthorityPage extends BasePage {

    public String pageTitle = "HMRC Tax Platform";
    public String expectedPageHeading = "Authority to interact with HMRC on your behalf";

    @FindBy(id = "authorise")
    private WebElement authorise;

    @FindBy(css = "#main-content > div > div > h1")
    WebElement pageHeading;

    @FindBy(css = "#content > div > div > p:nth-child(2) > strong")
    WebElement applicationName;

    public String getPageHeading() {
        return pageHeading.getText();
    }

    public String getApplicationName() {
        return applicationName.getText();
    }

    public LocalhostRedirectPage submit() {
        authorise.click();
        return new LocalhostRedirectPage();
    }
}


