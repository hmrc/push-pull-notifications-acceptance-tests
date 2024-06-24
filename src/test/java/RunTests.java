import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import utilities.configuration.HasConfiguration;

//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("features")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,timeline:build/test-results/timeline,rerun:target/failed_scenarios.txt")
// Junit 5's @Suite annotation currently doesn't play well with either the run mechanism or 'org.gradle.test-retry' version '1.5.8' and 'com.gradle.cucumber.companion' version '1.0.1' so we can't upgrade to it yet
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/resources/features",
        plugin = {"pretty", "timeline:build/test-results/timeline", "rerun:target/failed_scenarios.txt"}
)
public class RunTests extends HasConfiguration {}
