package pages.oauth;

import components.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;

public class OAuthErrorPage extends BasePage {

    @FindBy(xpath = "//pre")
    WebElement error;

    public String getErrorText() {
        return error.getText();
    }
}
