package components;

import configuration.Configuration;
import net.thucydides.core.pages.PageObject;

abstract public class BasePage extends PageObject {

    protected Configuration config = new Configuration();

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    protected String getDomain() {
        return config.baseUrl();
    }
}
