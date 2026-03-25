package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner Class
 * Cucumber TestNG test runner for executing feature files
 * Configures Cucumber options and execution parameters
 */
@CucumberOptions(
        features = "src/test/resources/features",
    glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/index.html",
                "junit:target/cucumber-reports/cucumber-results.xml"
        },
        monochrome = true,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * DataProvider for parallel execution
     * Runs scenarios in parallel
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
