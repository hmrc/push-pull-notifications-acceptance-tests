package steps.helpers;

import net.thucydides.core.annotations.Step;
import steps.apis.CommonApiSteps;

public class RequestPayloadHelper extends CommonApiSteps {

    @Step
    public void withXmlBodyFromFile(String filePath) {
        builder().withXmlBodyFromFile(filePath);
    }
}
