import configuration.HasConfiguration;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.auth.AuthType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.commons.lang3.Validate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features="classpath:features",
    plugin= {"rerun:rerun/failed_scenarios.txt"}
)
public class RunTests extends HasConfiguration {

}
