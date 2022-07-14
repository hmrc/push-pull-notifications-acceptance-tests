import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.junit.Assert.fail;

public class Hooks {

    @Before
    public void deleteAllCookies(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@dev-hub") ||
                scenario.getSourceTagNames().contains("@gatekeeper") ||
                scenario.getSourceTagNames().contains("@mfa") ||
                scenario.getSourceTagNames().contains("@api-documentation"))
        {
            getDriver().manage().deleteAllCookies();
        }
    }

    @Before
    public void maximiseWindow(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@dev-hub") ||
                scenario.getSourceTagNames().contains("@gatekeeper") ||
                scenario.getSourceTagNames().contains("@mfa") ||
                scenario.getSourceTagNames().contains("@api-documentation"))
        {
            getDriver().manage().window().maximize();
        }
    }

    @After
    public void assertForContentSecurityPolicyViolations(Scenario scenario) {
        if ((scenario.getSourceTagNames().contains("@dev-hub") ||
                scenario.getSourceTagNames().contains("@gatekeeper") ||
                scenario.getSourceTagNames().contains("@csp") ||
                scenario.getSourceTagNames().contains("@api-documentation")
        ) && scenario.getStatus() == Status.PASSED) {
            assertContentSecurityPolicy();
        }
    }

    private void assertContentSecurityPolicy() {
        List<LogEntry> cspViolations =
                getDriver().manage()
                        .logs().get(LogType.BROWSER)
                        .getAll()
                        .stream()
                        .filter(this::isContentSecurityPolicyErrorMessage)
                        .collect(Collectors.toList());

        if (!cspViolations.isEmpty()) {
            String cspViolationText = cspViolations.stream()
                    .map(LogEntry::getMessage)
                    .collect(Collectors.joining());

            String errorMessage = MessageFormat.format("Browser Content Security Policy violations:\n{0}",
                    cspViolationText);

            fail(errorMessage);
        }
    }

    private boolean isContentSecurityPolicyErrorMessage(LogEntry le) {
        return le.getMessage().contains("Content Security Policy");
    }
}
