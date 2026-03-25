package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader Utility Class
 * Reads configuration values from properties file
 * Follows Singleton Pattern for centralized config management
 */
public class ConfigReader {

    private static ConfigReader instance;
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";

    private ConfigReader() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + CONFIG_FILE_PATH);
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
        }
    }

    /**
     * Get singleton instance of ConfigReader
     * @return ConfigReader instance
     */
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("Property key not found: " + key);
            return "";
        }
        return value;
    }

    /**
     * Get property value with default fallback
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get browser name from config
     * @return Browser name (chrome, firefox, edge, safari)
     */
    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Check if headless mode is enabled
     * @return true if headless, false otherwise
     */
    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    /**
     * Get base URL for application under test
     * @return Base URL
     */
    public String getBaseUrl() {
        return getProperty("base.url");
    }

    /**
     * Get implicit wait timeout
     * @return Implicit wait in seconds
     */
    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait timeout
     * @return Explicit wait in seconds
     */
    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }

    /**
     * Get page load timeout
     * @return Page load timeout in seconds
     */
    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "20"));
    }

    /**
     * Get environment name
     * @return Environment (dev, test, staging, prod)
     */
    public String getEnvironment() {
        return getProperty("environment", "test");
    }

    /**
     * Check if screenshot capture is enabled on failure
     * @return true if enabled, false otherwise
     */
    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure", "true"));
    }

    /**
     * Get screenshot directory path
     * @return Screenshot path
     */
    public String getScreenshotPath() {
        return getProperty("screenshot.path", "./target/screenshots/");
    }

    /**
     * Get report directory path
     * @return Report path
     */
    public String getReportPath() {
        return getProperty("report.path", "./target/cucumber-reports/");
    }

}
