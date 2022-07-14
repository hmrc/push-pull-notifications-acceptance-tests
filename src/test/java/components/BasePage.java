package components;

import configuration.Configuration;
import net.thucydides.core.pages.PageObject;

abstract public class BasePage extends PageObject {

    protected Configuration config = new Configuration();

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    protected String getDomain() {
        return config.baseUrl();
    }

    protected void goToDomain(String url) {
        getDriver().manage().deleteAllCookies();
        getDriver().get(getDomain() + url);
    }
}
