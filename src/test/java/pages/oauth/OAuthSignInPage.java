package pages.oauth;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@DefaultUrl("https://www.qa.tax.service.gov.uk/oauth/authorize")
@At("https://www.qa.tax.service.gov.uk/oauth/authorize")
public class OAuthSignInPage extends BasePage {

    public String pageTitle = "Sign in using Government Gateway - GOV.UK";

    @FindBy(id = "user_id")
    WebElement userIdField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "continue")
    WebElement signInButton;

    public void goToSignInPage(String clientId, String scopes) {
        String url = String.format(
                "/oauth/authorize?response_type=code&client_id=%s&scope=%s&redirect_uri=urn:ietf:wg:oauth:2.0:oob",
                clientId,
                Stream.of(scopes.split(",")).collect(Collectors.joining("%20")));

        goToDomain(url);
    }

    public void enterUserId(String userId) {
        userIdField.sendKeys(userId);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public OutOfBandAuthCodePage signIn() {
        signInButton.click();
        return new OutOfBandAuthCodePage();
    }
}
