import configuration.HasConfiguration;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"rerun:rerun/failed_scenarios.txt"}
)
public class RunTests extends HasConfiguration {
}
