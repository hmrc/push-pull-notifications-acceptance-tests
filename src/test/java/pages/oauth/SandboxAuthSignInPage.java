package pages.oauth;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;

//@DefaultUrl("https://www.development.tax.service.gov.uk/oauth/authorize")
//@At("https://www.qa.tax.service.gov.uk/oauth/authorize")
public class SandboxAuthSignInPage extends BasePage {

    public String pageTitle = "Sign in";

    @FindBy(id = "userId")
    WebElement userIdField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement signInButton;

    public void enterUserId(String userId) {
        userIdField.sendKeys(userId);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void signIn() {
        signInButton.click();
    }
}
