# Selenium Cucumber Automation Framework

## Overview
This project is a test automation framework for web UI validation using Selenium WebDriver, Cucumber (BDD), and TestNG.

## Project Purpose
- Automate login and related UI scenarios on SauceDemo
- Maintain readable test cases with Gherkin feature files
- Use Page Object Model for reusable and maintainable test code

## Main Stack
- Java 11
- Selenium WebDriver
- Cucumber
- TestNG
- Maven
- Log4j2

## Core Structure
- src/test/java/hooks: Cucumber setup and teardown hooks
- src/test/java/pages: Page Object classes
- src/test/java/stepDefinitions: Cucumber step definitions
- src/test/java/runners: Test runner
- src/test/java/utils: Utility classes (driver, waits, config, screenshots)
- src/test/resources/features: Feature files
- src/test/resources/config: Runtime configuration

## How To Run
1. Open terminal in project root.
2. Build once:
   mvn clean install -DskipTests
3. Run tests:
   mvn clean test

## Reports
- Cucumber reports: target/cucumber-reports
- Failure screenshots: target/screenshots

## Notes
- Browser and timeout settings are configured in src/test/resources/config/config.properties.
- Ensure Java and Maven are installed and available in PATH before running tests.
