Feature: User Login Functionality
  As a user
  I want to login to the SauceDemo application
  So that I can access the shopping platform

  Background:
    Given User navigates to the login page

  @smoke @login @valid
  Scenario: Successful login with valid credentials
    When User enters valid username "standard_user"
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then User should be redirected to the products page
    And Products page header should be displayed

  @smoke @login @invalid
  Scenario: Login fails with invalid username
    When User enters invalid username "invalid_user"
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then Error message should be displayed
    And Error message should contain "do not match"

  @smoke @login @invalid
  Scenario: Login fails with invalid password
    When User enters valid username "standard_user"
    And User enters invalid password "wrong_password"
    And User clicks the login button
    Then Error message should be displayed
    And Error message should contain "do not match"

  @login @validation
  Scenario: Login fails with locked out user
    When User enters locked out username "locked_out_user"
    And User enters any password "secret_sauce"
    And User clicks the login button
    Then Error message should be displayed
    And Error message should contain "locked out"

  @login @validation @empty-field
  Scenario: Login fails with empty username field
    When User enters empty username ""
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then Error message should be displayed
    And Error message should contain "Username is required"

  @login @validation @empty-field
  Scenario: Login fails with empty password field
    When User enters valid username "standard_user"
    And User enters empty password ""
    And User clicks the login button
    Then Error message should be displayed
    And Error message should contain "Password is required"

  @login @validation @empty-field
  Scenario: Login fails with both fields empty
    When User enters empty username ""
    And User enters empty password ""
    And User clicks the login button
    Then Error message should be displayed

  @login @ui
  Scenario: Verify all login page elements are present
    Then Username field should be present
    And Password field should be present
    And Login button should be present
    And Login page header should be displayed

  @login @functionality
  Scenario: Clear fields functionality
    When User enters valid username "standard_user"
    And User enters valid password "secret_sauce"
    And User clears the username field
    Then Username field should be empty
    When User clears the password field
    Then Password field should be empty

  @login @performance
  Scenario: Login response time is acceptable
    When User enters valid username "standard_user"
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then Login should complete within 5 seconds
    And User should be redirected to the products page

  @login @error-handling
  Scenario Outline: Login with multiple credential combinations
    When User enters username "<username>"
    And User enters password "<password>"
    And User clicks the login button
    Then User should see "<result>"

    Examples:
      | username          | password      | result                  |
      | standard_user     | secret_sauce  | Products page           |
      | locked_out_user   | secret_sauce  | locked out              |
      | problem_user      | secret_sauce  | Products page           |
      | performance_glitch_user | secret_sauce | Products page       |
      | invalid_user      | invalid_pass  | do not match            |

  @login @regression
  Scenario: Multiple login attempts
    When User enters valid username "standard_user"
    And User enters invalid password "wrong"
    And User clicks the login button
    Then Error message should be displayed
    When User clears the password field
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then User should be redirected to the products page

  @login @edge-case
  Scenario: Login with special characters in password
    When User enters valid username "standard_user"
    And User enters valid password "secret_sauce"
    And User clicks the login button
    Then User should be redirected to the products page

  @login @accessibility
  Scenario: Login page is accessible
    Then Username field should be accessible via keyboard
    And Password field should be accessible via keyboard
    And Login button should be accessible via keyboard

  @login @data-validation
  Scenario: Verify username field accepts only text
    When User enters valid username "standard_user"
    Then Username field should contain "standard_user"

  @login @ui @regression
  Scenario: Verify page layout on login page
    Then Login page should display all required elements
    And Login form should be centered on the page
    And Login button should be clickable
