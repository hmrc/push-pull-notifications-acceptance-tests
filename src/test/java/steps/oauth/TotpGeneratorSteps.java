package steps.oauth;

import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.Validate;
import steps.apis.CommonApiSteps;
import uk.gov.hmrc.totp.JavaTotpSha512Generator;

public class TotpGeneratorSteps extends CommonApiSteps {

    static String totpCode = null;
    NextTotpGenerator nextTotpGenerator = new NextTotpGenerator(new JavaTotpSha512Generator());

    @Step
    public String getTotpCode() {
        return Validate.notNull(totpCode, "TOTP code not generated - need to generate it first");
    }
}
