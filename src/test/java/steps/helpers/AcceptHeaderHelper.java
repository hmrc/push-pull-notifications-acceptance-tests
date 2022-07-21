package steps.helpers;

import net.thucydides.core.annotations.Step;
import steps.apis.CommonApiSteps;

public class AcceptHeaderHelper extends CommonApiSteps {

    @Step
    public void withHmrcJsonAcceptHeader() {
        withHmrcJsonAcceptHeader("1.0");
    }

    public void withHmrcJsonAcceptHeaderVersion(String version) {
        withHmrcJsonAcceptHeader(version);
    }

    @Step
    public void withHmrcJsonAcceptHeaderWithProxy() {
        withHmrcJsonAcceptHeaderWithProxy("1.0");
    }

    @Step
    public void withHmrcJsonAcceptHeader(String version) {
        builder().setAccept(String.format("application/vnd.hmrc.%s+json", version));
    }

    @Step
    public void withHmrcJsonAcceptHeaderWithProxy(String version) {
        builder().withProxy().setAccept(String.format("application/vnd.hmrc.%s+json", version));
    }

    @Step
    public void withIncorrectAcceptHeaderVersion() {
        builder().setAccept("application/vnd.hmrc.99.0+json");
    }

    @Step
    public void withInvalidAcceptHeader() {
        builder().setAccept("application/vnd.xyz.1.0+json");
    }

    @Step
    public void withNoAcceptHeader() {
        builder().setNoAccept();
    }

}
