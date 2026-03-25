package pages;

import utils.DriverFactory;
import utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage Class
 * Page Object Model for Login page of SauceDemo application
 * Encapsulates all login-related elements and operations
 */
public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    // Locators using @FindBy annotation
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[contains(text(), 'Swag Labs')]")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='error-message-container']/h3")
    private WebElement errorMessage;

    // Locators using By for dynamic usage
    private static final By USERNAME_LOCATOR = By.id("user-name");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("login-button");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@class='error-message-container']/h3");
    private static final By PAGE_TITLE_LOCATOR = By.xpath("//div[@class='login_logo']");

    /**
     * Constructor - Initialize WebDriver and PageFactory
     */
    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        logger.info("LoginPage initialized");
    }

    /**
     * Verify if login page is displayed
     * @return true if login page is visible
     */
    public boolean isLoginPageDisplayed() {
        try {
            WaitUtils.waitForElementToBeVisible(driver, USERNAME_LOCATOR);
            logger.info("Login page is displayed");
            return true;
        } catch (Exception e) {
            logger.warn("Login page is not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get page title/header
     * @return Header text
     */
    public String getPageHeader() {
        try {
            String header = WaitUtils.waitForElementToBeVisible(driver, PAGE_TITLE_LOCATOR).getText();
            logger.info("Page header: " + header);
            return header;
        } catch (Exception e) {
            logger.error("Error getting page header: " + e.getMessage());
            return "";
        }
    }

    /**
     * Enter username in the username field
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        try {
            WebElement element = WaitUtils.waitForElementToBeVisible(driver, USERNAME_LOCATOR);
            element.clear();
            element.sendKeys(username);
            logger.info("Username entered: " + username);
        } catch (Exception e) {
            logger.error("Error entering username: " + e.getMessage());
            throw new RuntimeException("Failed to enter username: " + e.getMessage());
        }
    }

    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        try {
            WebElement element = WaitUtils.waitForElementToBeVisible(driver, PASSWORD_LOCATOR);
            element.clear();
            element.sendKeys(password);
            logger.info("Password entered (hidden for security)");
        } catch (Exception e) {
            logger.error("Error entering password: " + e.getMessage());
            throw new RuntimeException("Failed to enter password: " + e.getMessage());
        }
    }

    /**
     * Click the login button
     */
    public void clickLoginButton() {
        try {
            WebElement button = WaitUtils.waitForElementToBeClickable(driver, LOGIN_BUTTON_LOCATOR);
            button.click();
            logger.info("Login button clicked");
        } catch (Exception e) {
            logger.error("Error clicking login button: " + e.getMessage());
            throw new RuntimeException("Failed to click login button: " + e.getMessage());
        }
    }

    /**
     * Perform login with username and password
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        logger.info("Attempting login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login attempted");
    }

    /**
     * Get error message displayed on login page
     * @return Error message text
     */
    public String getErrorMessage() {
        try {
            WebElement error = WaitUtils.waitForElementToBeVisible(driver, ERROR_MESSAGE_LOCATOR);
            String errorText = error.getText();
            logger.info("Error message retrieved: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No error message found: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        try {
            WaitUtils.waitForElementToBeVisible(driver, ERROR_MESSAGE_LOCATOR);
            logger.info("Error message is displayed");
            return true;
        } catch (Exception e) {
            logger.warn("Error message is not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get username field value
     * @return Current value in username field
     */
    public String getUsernameFieldValue() {
        try {
            String value = WaitUtils.waitForElementToBeVisible(driver, USERNAME_LOCATOR).getAttribute("value");
            logger.info("Username field value retrieved");
            return value;
        } catch (Exception e) {
            logger.error("Error getting username field value: " + e.getMessage());
            return "";
        }
    }

    /**
     * Clear username field
     */
    public void clearUsernameField() {
        try {
            WebElement element = WaitUtils.waitForElementToBeVisible(driver, USERNAME_LOCATOR);
            element.clear();
            logger.info("Username field cleared");
        } catch (Exception e) {
            logger.error("Error clearing username field: " + e.getMessage());
            throw new RuntimeException("Failed to clear username field: " + e.getMessage());
        }
    }

    /**
     * Clear password field
     */
    public void clearPasswordField() {
        try {
            WebElement element = WaitUtils.waitForElementToBeVisible(driver, PASSWORD_LOCATOR);
            element.clear();
            logger.info("Password field cleared");
        } catch (Exception e) {
            logger.error("Error clearing password field: " + e.getMessage());
            throw new RuntimeException("Failed to clear password field: " + e.getMessage());
        }
    }

    /**
     * Verify username field is present
     * @return true if username field exists
     */
    public boolean isUsernameFieldPresent() {
        try {
            driver.findElement(USERNAME_LOCATOR);
            logger.info("Username field is present");
            return true;
        } catch (Exception e) {
            logger.warn("Username field not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify password field is present
     * @return true if password field exists
     */
    public boolean isPasswordFieldPresent() {
        try {
            driver.findElement(PASSWORD_LOCATOR);
            logger.info("Password field is present");
            return true;
        } catch (Exception e) {
            logger.warn("Password field not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify login button is present
     * @return true if login button exists
     */
    public boolean isLoginButtonPresent() {
        try {
            driver.findElement(LOGIN_BUTTON_LOCATOR);
            logger.info("Login button is present");
            return true;
        } catch (Exception e) {
            logger.warn("Login button not found: " + e.getMessage());
            return false;
        }
    }

}
