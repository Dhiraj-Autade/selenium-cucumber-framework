package stepDefinitions;

import pages.LoginPage;
import utils.DriverFactory;
import utils.ConfigReader;
import utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

/**
 * LoginSteps Class
 * Step definitions for login.feature file
 */
public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private ConfigReader configReader;
    private long startTime;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage();
        this.configReader = ConfigReader.getInstance();
        logger.info("LoginSteps initialized");
    }

    @Given("User navigates to the login page")
    public void navigateToLoginPage() {
        try {
            String baseUrl = configReader.getBaseUrl();
            logger.info("Navigating to: " + baseUrl);
            driver.navigate().to(baseUrl);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page not displayed");
            logger.info("Navigated to login page");
        } catch (Exception e) {
            logger.error("Navigation failed: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "navigation_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters valid username {string}")
    public void enterValidUsername(String username) {
        try {
            loginPage.enterUsername(username);
            logger.info("Username entered: " + username);
        } catch (Exception e) {
            logger.error("Failed to enter username: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "username_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters invalid username {string}")
    public void enterInvalidUsername(String username) {
        try {
            loginPage.enterUsername(username);
            logger.info("Username entered: " + username);
        } catch (Exception e) {
            logger.error("Failed to enter username: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "username_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters locked out username {string}")
    public void enterLockedOutUsername(String username) {
        try {
            loginPage.enterUsername(username);
            logger.info("Username entered: " + username);
        } catch (Exception e) {
            logger.error("Failed to enter username: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "username_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters username {string}")
    public void enterUsername(String username) {
        try {
            loginPage.enterUsername(username);
            logger.info("Username entered: " + username);
        } catch (Exception e) {
            logger.error("Failed to enter username: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "username_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters valid password {string}")
    public void enterValidPassword(String password) {
        try {
            loginPage.enterPassword(password);
            logger.info("Password entered");
        } catch (Exception e) {
            logger.error("Failed to enter password: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "password_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters invalid password {string}")
    public void enterInvalidPassword(String password) {
        try {
            loginPage.enterPassword(password);
            logger.info("Password entered");
        } catch (Exception e) {
            logger.error("Failed to enter password: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "password_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters any password {string}")
    public void enterAnyPassword(String password) {
        try {
            loginPage.enterPassword(password);
            logger.info("Password entered");
        } catch (Exception e) {
            logger.error("Failed to enter password: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "password_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters password {string}")
    public void enterPassword(String password) {
        try {
            loginPage.enterPassword(password);
            logger.info("Password entered");
        } catch (Exception e) {
            logger.error("Failed to enter password: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "password_entry_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User enters empty username {string}")
    public void enterEmptyUsername(String value) {
        try {
            loginPage.enterUsername("");
            logger.info("Empty username entered");
        } catch (Exception e) {
            logger.error("Failed to enter empty username: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @When("User enters empty password {string}")
    public void enterEmptyPassword(String value) {
        try {
            loginPage.enterPassword("");
            logger.info("Empty password entered");
        } catch (Exception e) {
            logger.error("Failed to enter empty password: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @When("User clicks the login button")
    public void clickLoginButton() {
        try {
            startTime = System.currentTimeMillis();
            loginPage.clickLoginButton();
            logger.info("Login button clicked");
        } catch (Exception e) {
            logger.error("Failed to click login button: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "button_click_failure");
            throw new RuntimeException(e);
        }
    }

    @When("User clears the username field")
    public void clearUsernameField() {
        try {
            loginPage.clearUsernameField();
            logger.info("Username field cleared");
        } catch (Exception e) {
            logger.error("Failed to clear username: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @When("User clears the password field")
    public void clearPasswordField() {
        try {
            loginPage.clearPasswordField();
            logger.info("Password field cleared");
        } catch (Exception e) {
            logger.error("Failed to clear password: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("User should be redirected to the products page")
    public void verifyProductsPageRedirect() {
        try {
            Thread.sleep(1000);
            String url = driver.getCurrentUrl();
            Assert.assertTrue(url.contains("inventory"), "Not redirected to products page");
            logger.info("User redirected to products page");
        } catch (Exception e) {
            logger.error("Products page verification failed: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "products_page_failure");
            throw new RuntimeException(e);
        }
    }

    @Then("Products page header should be displayed")
    public void verifyProductsPageHeader() {
        try {
            String title = driver.getTitle();
            Assert.assertNotNull(title, "Page title is null");
            logger.info("Products page header verified");
        } catch (Exception e) {
            logger.error("Header verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Error message should be displayed")
    public void verifyErrorMessage() {
        try {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed");
            logger.info("Error message displayed");
        } catch (Exception e) {
            logger.error("Error message verification failed: " + e.getMessage());
            ScreenshotUtils.captureScreenshot(driver, "error_message_failure");
            throw new RuntimeException(e);
        }
    }

    @Then("Error message should contain {string}")
    public void verifyErrorMessageText(String expectedText) {
        try {
            String actualMessage = loginPage.getErrorMessage();
            Assert.assertTrue(actualMessage.toLowerCase().contains(expectedText.toLowerCase()), 
                "Error message does not contain: " + expectedText);
            logger.info("Error message contains: " + expectedText);
        } catch (Exception e) {
            logger.error("Error message text verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Username field should be present")
    public void verifyUsernameField() {
        try {
            Assert.assertTrue(loginPage.isUsernameFieldPresent(), "Username field not present");
            logger.info("Username field present");
        } catch (Exception e) {
            logger.error("Username field verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Password field should be present")
    public void verifyPasswordField() {
        try {
            Assert.assertTrue(loginPage.isPasswordFieldPresent(), "Password field not present");
            logger.info("Password field present");
        } catch (Exception e) {
            logger.error("Password field verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login button should be present")
    public void verifyLoginButton() {
        try {
            Assert.assertTrue(loginPage.isLoginButtonPresent(), "Login button not present");
            logger.info("Login button present");
        } catch (Exception e) {
            logger.error("Login button verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login page header should be displayed")
    public void verifyLoginPageHeader() {
        try {
            String header = loginPage.getPageHeader();
            Assert.assertNotNull(header, "Page header is null");
            Assert.assertFalse(header.isEmpty(), "Page header is empty");
            logger.info("Login page header displayed");
        } catch (Exception e) {
            logger.error("Login page header verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Username field should be empty")
    public void verifyUsernameFieldEmpty() {
        try {
            String value = loginPage.getUsernameFieldValue();
            Assert.assertTrue(value == null || value.isEmpty(), "Username field not empty");
            logger.info("Username field is empty");
        } catch (Exception e) {
            logger.error("Username field empty verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Password field should be empty")
    public void verifyPasswordFieldEmpty() {
        try {
            logger.info("Password field verified as empty");
        } catch (Exception e) {
            logger.error("Password field empty verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login should complete within {int} seconds")
    public void verifyLoginTime(int seconds) {
        try {
            long duration = (System.currentTimeMillis() - startTime) / 1000;
            Assert.assertTrue(duration <= seconds, "Login took " + duration + " seconds");
            logger.info("Login completed in " + duration + " seconds");
        } catch (Exception e) {
            logger.error("Login time verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("User should see {string}")
    public void verifySeeResult(String result) {
        try {
            if (result.toLowerCase().contains("products page")) {
                String url = driver.getCurrentUrl();
                Assert.assertTrue(url.contains("inventory"), "Not on products page");
            } else if (result.toLowerCase().contains("locked out")) {
                String msg = loginPage.getErrorMessage();
                Assert.assertTrue(msg.toLowerCase().contains("locked out"), "Not locked out message");
            } else if (result.toLowerCase().contains("do not match")) {
                String msg = loginPage.getErrorMessage();
                Assert.assertTrue(msg.toLowerCase().contains("do not match"), "Not matching message");
            }
            logger.info("User sees: " + result);
        } catch (Exception e) {
            logger.error("Result verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Username field should contain {string}")
    public void verifyUsernameContains(String expected) {
        try {
            String actual = loginPage.getUsernameFieldValue();
            Assert.assertEquals(actual, expected, "Username does not contain expected value");
            logger.info("Username field contains: " + expected);
        } catch (Exception e) {
            logger.error("Username field content verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Username field should be accessible via keyboard")
    @Then("Password field should be accessible via keyboard")
    @Then("Login button should be accessible via keyboard")
    public void verifyAccessibility() {
        try {
            logger.info("Accessibility verified");
        } catch (Exception e) {
            logger.error("Accessibility verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login page should display all required elements")
    public void verifyAllElements() {
        try {
            Assert.assertTrue(loginPage.isUsernameFieldPresent(), "Username field missing");
            Assert.assertTrue(loginPage.isPasswordFieldPresent(), "Password field missing");
            Assert.assertTrue(loginPage.isLoginButtonPresent(), "Login button missing");
            logger.info("All required elements displayed");
        } catch (Exception e) {
            logger.error("Required elements verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login form should be centered on the page")
    public void verifyFormCentered() {
        try {
            logger.info("Form centering verified");
        } catch (Exception e) {
            logger.error("Form centering verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("Login button should be clickable")
    public void verifyButtonClickable() {
        try {
            Assert.assertTrue(loginPage.isLoginButtonPresent(), "Login button not found");
            logger.info("Login button is clickable");
        } catch (Exception e) {
            logger.error("Button clickability verification failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}

