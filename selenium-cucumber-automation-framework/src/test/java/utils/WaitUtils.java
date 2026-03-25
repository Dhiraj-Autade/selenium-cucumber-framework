package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitUtils Class
 * Provides all explicit wait operations
 * Centralizes wait logic for better maintainability
 */
public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);

    /**
     * Wait for element to be visible
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param timeoutSeconds Maximum wait time in seconds
     * @return WebElement once visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By by, int timeoutSeconds) {
        logger.debug("Waiting for element to be visible: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Timeout waiting for element visibility: " + by);
            throw new RuntimeException("Element not visible within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be visible with default timeout
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @return WebElement once visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By by) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForElementToBeVisible(driver, by, timeout);
    }

    /**
     * Wait for element to be clickable
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param timeoutSeconds Maximum wait time in seconds
     * @return WebElement once clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By by, int timeoutSeconds) {
        logger.debug("Waiting for element to be clickable: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.error("Timeout waiting for element to be clickable: " + by);
            throw new RuntimeException("Element not clickable within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be clickable with default timeout
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @return WebElement once clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By by) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForElementToBeClickable(driver, by, timeout);
    }

    /**
     * Wait for element to be present in DOM
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param timeoutSeconds Maximum wait time in seconds
     * @return WebElement once present
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By by, int timeoutSeconds) {
        logger.debug("Waiting for element to be present: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Timeout waiting for element presence: " + by);
            throw new RuntimeException("Element not present within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be present with default timeout
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @return WebElement once present
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By by) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForElementToBePresent(driver, by, timeout);
    }

    /**
     * Wait for element to be invisible/disappear
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param timeoutSeconds Maximum wait time in seconds
     * @return true if element becomes invisible
     */
    public static boolean waitForElementToBeInvisible(WebDriver driver, By by, int timeoutSeconds) {
        logger.debug("Waiting for element to be invisible: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Timeout waiting for element invisibility: " + by);
            throw new RuntimeException("Element did not become invisible within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for element to be invisible with default timeout
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @return true if element becomes invisible
     */
    public static boolean waitForElementToBeInvisible(WebDriver driver, By by) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForElementToBeInvisible(driver, by, timeout);
    }

    /**
     * Wait for URL to be a certain value
     * @param driver WebDriver instance
     * @param url Expected URL
     * @param timeoutSeconds Maximum wait time in seconds
     * @return true if URL matches
     */
    public static boolean waitForUrlContains(WebDriver driver, String url, int timeoutSeconds) {
        logger.debug("Waiting for URL to contain: " + url);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            logger.error("Timeout waiting for URL: " + url);
            throw new RuntimeException("URL did not contain " + url + " within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for URL to be a certain value with default timeout
     * @param driver WebDriver instance
     * @param url Expected URL
     * @return true if URL matches
     */
    public static boolean waitForUrlContains(WebDriver driver, String url) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForUrlContains(driver, url, timeout);
    }

    /**
     * Wait for text to be present in element
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param text Text to find
     * @param timeoutSeconds Maximum wait time in seconds
     * @return true if text is present
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By by, String text, int timeoutSeconds) {
        logger.debug("Waiting for text '" + text + "' to be present in element: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
        } catch (Exception e) {
            logger.error("Timeout waiting for text in element: " + by);
            throw new RuntimeException("Text not found within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

    /**
     * Wait for text to be present in element with default timeout
     * @param driver WebDriver instance
     * @param by Locator strategy
     * @param text Text to find
     * @return true if text is present
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By by, String text) {
        int timeout = ConfigReader.getInstance().getExplicitWait();
        return waitForTextToBePresentInElement(driver, by, text, timeout);
    }

    /**
     * Generic wait with custom condition
     * @param driver WebDriver instance
     * @param timeoutSeconds Timeout in seconds
     * @param condition Custom condition to wait for
     * @return true if condition is met
     */
    public static <T> T waitForCustomCondition(WebDriver driver, int timeoutSeconds, 
                                               org.openqa.selenium.support.ui.ExpectedCondition<T> condition) {
        logger.debug("Waiting for custom condition with timeout: " + timeoutSeconds + " seconds");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(condition);
        } catch (Exception e) {
            logger.error("Timeout waiting for custom condition");
            throw new RuntimeException("Custom condition not met within " + timeoutSeconds + " seconds: " + e.getMessage());
        }
    }

}
