package pages.oauth;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@DefaultUrl("https://www.ete.access.tax.service.gov.uk/login/signin/creds")
@At("https://www.ete.access.tax.service.gov.uk/login/signin/creds")
public class ScpSignInPage extends BasePage {

    public String pageTitle = "Sign in using Government Gateway - GOV.UK";
    public String SignedInPageTitle = "You are already signed in - GOV.UK\"";

    @FindBy(id = "user_id")
    WebElement userIdField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "continue")
    WebElementFacade signInButton;

    public void signIn(String ggUserName, String ggPassword) {
        if (getCurrentUrl().contains("login/already-signed-in")) {
            getTitle().equals(SignedInPageTitle);
            getDriver().findElement(By.id("Switch")).click();
            getDriver().findElement(By.id("continue")).click();
        }

        new WebDriverWait(getDriver(),30).until(ExpectedConditions.titleIs(pageTitle));
        signInButton.waitUntilClickable();
        userIdField.sendKeys(ggUserName);
        passwordField.sendKeys(ggPassword);
        signInButton.click();
    }
}
