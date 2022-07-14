package definitions;

import net.thucydides.core.annotations.Steps;
import steps.apis.CommonResponseSteps;

public class CommonDefinitions {

    @Steps(shared = true)
    protected CommonResponseSteps responseSteps;
}
