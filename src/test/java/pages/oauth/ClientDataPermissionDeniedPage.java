package pages.oauth;

import components.BasePage;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/oauth/.*.")
@At("https://.*./oauth/.*.")
public class ClientDataPermissionDeniedPage extends BasePage {

    public String pageTitle = "HMRC Tax Platform - Permission denied";
}
