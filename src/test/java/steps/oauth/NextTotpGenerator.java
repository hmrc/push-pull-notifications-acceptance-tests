package steps.oauth;

import uk.gov.hmrc.totp.HmacShaTotp;

public class NextTotpGenerator {

    private HmacShaTotp totpGenerator;

    public NextTotpGenerator(HmacShaTotp totpGenerator){
        this.totpGenerator = totpGenerator;
    }

    // Because we need to hold this across tests to ensure we get new tokens each time, this must be static...
    private static String totpCode = null;

    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isSameAsPreviousTotpCode(String newTotpCode) {
        return (newTotpCode.equals(totpCode));
    }

    public String getNextTotpCode(String totpSecret) {
        String newTotpCode = totpGenerator.getTotpCode(totpSecret.toUpperCase());
        while(isSameAsPreviousTotpCode(newTotpCode)) {
            safeSleep(500);
            newTotpCode = totpGenerator.getTotpCode(totpSecret.toUpperCase());
        }
        totpCode = newTotpCode;

        return (totpCode);
    }
}
