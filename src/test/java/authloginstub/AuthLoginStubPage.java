//package authloginstub;
//
//import components.BasePage;
//import net.serenitybdd.core.annotations.findby.FindBy;
//import net.thucydides.core.annotations.At;
//import net.thucydides.core.annotations.DefaultUrl;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Select;
//
//@DefaultUrl("/auth-login-stub/gg-sign-in")
//@At("https://.*./auth-login-stub/gg-sign-in")
//public class AuthLoginStubPage extends BasePage {
//
//    public String pageTitle = "Authority Wizard";
//
//    @FindBy(id = "authorityId")
//    WebElement credIdField;
//
//    @FindBy(id = "redirectionUrl")
//    WebElement redirectUrl;
//
//    @FindBy(id = "credentialStrength")
//    WebElement credentialStrengthDropDown;
//
//    @FindBy(id = "confidenceLevel")
//    WebElement confidenceLevel;
//
//    @FindBy(id = "submit-top")
//    WebElement submitButton;
//
//    @FindBy(id = "add-preset")
//    WebElement addPresetButton;
//
//    @FindBy(id = "nino")
//    WebElement ninoField;
//
//    @FindBy(id = "input-4-0-value")
//    WebElement saUtrField;
//
//    @FindBy(id = "affinityGroupSelect")
//    WebElement anfinityGroup;
//
//    @FindBy(css = "#js-enrolments-table > tbody > tr:nth-child(2) > td:nth-child(1) > input[type=\"text\"]")
//    WebElement enrolmentKey;
//
//    @FindBy(id = "input-0-0-name")
//    WebElement identifierName;
//
//    @FindBy(id = "input-0-0-value")
//    WebElement identifierValue;
//
//    public void fillCredId() {
//        credIdField.sendKeys("0000001750989867");
//    }
//
//    public void fillRedirectUrl(String scope) {
//        String keys = String.format("/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code", config.clientId(), config.authRedirectUri(), scope);
//        redirectUrl.sendKeys(keys);
//    }
//
//    public void selectCredentialStrength(String credStrength) {
//        Select dropdown =  new Select (credentialStrengthDropDown);
//        dropdown.selectByVisibleText(credStrength);
//    }
//
//    public GrantAuthorityPage submit() {
//        submitButton.click();
//        return new GrantAuthorityPage();
//    }
//
//    public void setConfidenceLevel(int level) {
//        Select dropdown =  new Select (confidenceLevel);
//        dropdown.selectByVisibleText(Integer.toString(level));
//    }
//
//    public void addNino() {
//        ninoField.click();
//        ninoField.sendKeys("CK562300D");
//    }
//
//    public void addSaUtr() {
//        addPresetButton.click();
//        saUtrField.sendKeys("6116471786");
//    }
//
//    public void selectAffinityGroup(String groupName) {
//        Select dropdown =  new Select (anfinityGroup);
//        dropdown.selectByVisibleText(groupName);
//    }
//
//    public void enterEnrolmentKey(String enrolmentKeyName) {
//        enrolmentKey.sendKeys(enrolmentKeyName);
//    }
//
//    public void identifierName(String identifierName) {
//        this.identifierName.sendKeys(identifierName);
//    }
//
//    public void identifierValue(String identifierValue) {
//        this.identifierValue.sendKeys(identifierValue);
//    }
//}