package hooks;

import utils.DriverFactory;
import utils.ScreenshotUtils;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Hooks Class
 * Cucumber hooks for test setup and teardown
 * Runs before and after each scenario
 */
public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    /**
     * Setup method - runs before each scenario
     * Initializes WebDriver and navigates to base URL
     */
    @Before
    public void setUp() {
        logger.info("========== TEST SCENARIO STARTED ==========");
        
        try {
            // Initialize WebDriver
            driver = DriverFactory.initializeDriver();
            DriverFactory.setDriver(driver);
            
            logger.info("WebDriver initialized successfully");
            logger.info("WebDriver initialized for browser automation");
            
        } catch (Exception e) {
            logger.error("Error during test setup: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }
    }

    /**
     * Teardown method - runs after each scenario
     * Takes screenshot on failure and quits WebDriver
     */
    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        logger.info("========== TEST SCENARIO ENDED ==========");
        logger.info("Scenario Status: " + scenario.getStatus());
        
        try {
            // Capture screenshot on failure
            if (scenario.isFailed()) {
                logger.warn("Scenario failed: " + scenario.getName());
                String screenshotPath = ScreenshotUtils.captureScreenshotOnFailure(driver, scenario.getName());
                
                if (screenshotPath != null) {
                    logger.info("Screenshot captured: " + screenshotPath);
                }
            } else {
                logger.info("Scenario passed: " + scenario.getName());
            }
            
        } catch (Exception e) {
            logger.error("Error during screenshot capture: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Quit WebDriver
            try {
                DriverFactory.quitDriver();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error closing WebDriver: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
