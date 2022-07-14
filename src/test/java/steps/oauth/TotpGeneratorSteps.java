package steps.oauth;

import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.Validate;
import steps.apis.CommonApiSteps;
import uk.gov.hmrc.totp.JavaTotpSha512Generator;

public class TotpGeneratorSteps extends CommonApiSteps {

    static String totpCode = null;
    NextTotpGenerator nextTotpGenerator = new NextTotpGenerator(new JavaTotpSha512Generator());

    @Step
    public void generateNextTotpCode() {
       totpCode = nextTotpGenerator.getNextTotpCode(config.privilegedTotpSecret());
    }

    @Step
    public void generateNextTotpCodeForPrivilegedAppWithMissingScopes() {
        totpCode = nextTotpGenerator.getNextTotpCode(config.privilegedTotpSecretWithMissingScopes());
    }

    @Step
    public void generateNextTotpCodeForPrivilegedAppWithNoScopes() {
        totpCode = nextTotpGenerator.getNextTotpCode(config.privilegedTotpSecretWithNoScopes());
    }

    @Step
    public void generateNextTotpCodeForPrivilegedAppWithNoSubscriptions() {
        totpCode = nextTotpGenerator.getNextTotpCode(config.privilegedTotpSecretWithNoSubscriptions());
    }

    @Step
    public String getTotpCode() {
        return Validate.notNull(totpCode, "TOTP code not generated - need to generate it first");
    }
}
