package pages.authloginstub;

import components.BasePage;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/oauth/grantscope")
@At("https://.*.//oauth/grantscope")
public class LocalhostRedirectPage extends BasePage {

    public String pageTitle = "localhost";

    public String getUrlAccessCode() {
        String[] accessCode = getDriver().getCurrentUrl().split("=");
        System.out.println("Generated Access Code:" + accessCode[1]);
        return accessCode[1];
    }
}
