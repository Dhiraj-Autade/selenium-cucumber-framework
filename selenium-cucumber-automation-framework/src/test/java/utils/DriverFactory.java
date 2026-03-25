package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

/**
 * DriverFactory Class
 * Responsible for WebDriver initialization and management
 * Supports Chrome, Firefox, Edge, and Safari browsers
 * Implements thread-local pattern for thread-safe WebDriver management
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on configuration
     * @return Initialized WebDriver
     */
    public static WebDriver initializeDriver() {
        ConfigReader configReader = ConfigReader.getInstance();
        String browser = configReader.getBrowser().toLowerCase();
        
        logger.info("Initializing WebDriver for browser: " + browser);
        
        WebDriver driver = null;

        switch (browser) {
            case "chrome":
                driver = initializeChromeDriver(configReader);
                break;
            case "firefox":
                driver = initializeFirefoxDriver(configReader);
                break;
            case "edge":
                driver = initializeEdgeDriver(configReader);
                break;
            case "safari":
                driver = initializeSafariDriver(configReader);
                break;
            default:
                logger.error("Unsupported browser: " + browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Set timeouts
        driver.manage().timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(configReader.getImplicitWait()))
                .pageLoadTimeout(java.time.Duration.ofSeconds(configReader.getPageLoadTimeout()));

        logger.info("WebDriver initialized successfully");
        return driver;
    }

    /**
     * Initialize Chrome Driver
     * @param configReader Configuration reader instance
     * @return Chrome WebDriver
     */
    private static WebDriver initializeChromeDriver(ConfigReader configReader) {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        
        if (configReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Chrome headless mode enabled");
        }
        
        // Additional Chrome options for stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        
        WebDriver driver = new ChromeDriver(options);
        logger.info("Chrome driver initialized");
        
        return driver;
    }

    /**
     * Initialize Firefox Driver
     * @param configReader Configuration reader instance
     * @return Firefox WebDriver
     */
    private static WebDriver initializeFirefoxDriver(ConfigReader configReader) {
        WebDriverManager.firefoxdriver().setup();
        
        FirefoxOptions options = new FirefoxOptions();
        
        if (configReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Firefox headless mode enabled");
        }
        
        options.addArguments("--start-maximized");
        
        WebDriver driver = new FirefoxDriver(options);
        logger.info("Firefox driver initialized");
        
        return driver;
    }

    /**
     * Initialize Edge Driver
     * @param configReader Configuration reader instance
     * @return Edge WebDriver
     */
    private static WebDriver initializeEdgeDriver(ConfigReader configReader) {
        WebDriverManager.edgedriver().setup();
        
        EdgeOptions options = new EdgeOptions();
        
        if (configReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Edge headless mode enabled");
        }
        
        options.addArguments("--start-maximized");
        
        WebDriver driver = new EdgeDriver(options);
        logger.info("Edge driver initialized");
        
        return driver;
    }

    /**
     * Initialize Safari Driver
     * @param configReader Configuration reader instance
     * @return Safari WebDriver
     */
    private static WebDriver initializeSafariDriver(ConfigReader configReader) {
        WebDriver driver = new SafariDriver();
        logger.info("Safari driver initialized");
        
        return driver;
    }

    /**
     * Set WebDriver instance in ThreadLocal
     * @param driver WebDriver instance
     */
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    /**
     * Get WebDriver instance from ThreadLocal
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.warn("WebDriver instance not found in ThreadLocal");
        }
        return driver;
    }

    /**
     * Quit WebDriver and clean up
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            logger.info("WebDriver closed and removed from ThreadLocal");
        }
    }

}
