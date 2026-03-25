package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils Class
 * Handles screenshot capture for test failures and reporting
 */
public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final ConfigReader configReader = ConfigReader.getInstance();

    /**
     * Capture screenshot and save to file system
     * @param driver WebDriver instance
     * @param screenshotName Name for the screenshot
     * @return Screenshot file path
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            logger.warn("WebDriver is null. Cannot capture screenshot.");
            return null;
        }

        try {
            // Create screenshot directory if not exists
            String screenshotPath = configReader.getScreenshotPath();
            File screenshotDir = new File(screenshotPath);
            
            if (!screenshotDir.exists()) {
                boolean created = screenshotDir.mkdirs();
                if (!created) {
                    logger.warn("Failed to create screenshot directory: " + screenshotPath);
                }
            }

            // Generate unique filename with timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String fullPath = screenshotPath + fileName;

            // Take screenshot using TakesScreenshot interface
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);

            // Write screenshot to file
            try (FileOutputStream fos = new FileOutputStream(fullPath)) {
                fos.write(screenshotBytes);
            }

            logger.info("Screenshot captured successfully: " + fullPath);
            return fullPath;

        } catch (IOException e) {
            logger.error("Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error during screenshot capture: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Capture screenshot with default naming convention
     * @param driver WebDriver instance
     * @return Screenshot file path
     */
    public static String captureScreenshot(WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
        return captureScreenshot(driver, "screenshot_" + timestamp);
    }

    /**
     * Capture screenshot on test failure
     * @param driver WebDriver instance
     * @param testName Test name/scenario name
     * @return Screenshot file path
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        if (!configReader.isScreenshotOnFailure()) {
            logger.info("Screenshot on failure is disabled in configuration");
            return null;
        }

        logger.info("Capturing screenshot for failed test: " + testName);
        return captureScreenshot(driver, "FAILURE_" + testName);
    }

    /**
     * Get all screenshots from directory
     * @return Array of screenshot files
     */
    public static File[] getAllScreenshots() {
        String screenshotPath = configReader.getScreenshotPath();
        File screenshotDir = new File(screenshotPath);

        if (!screenshotDir.exists()) {
            logger.warn("Screenshot directory does not exist: " + screenshotPath);
            return new File[0];
        }

        File[] screenshots = screenshotDir.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".png") || 
            name.toLowerCase().endsWith(".jpg") ||
            name.toLowerCase().endsWith(".jpeg")
        );

        return screenshots != null ? screenshots : new File[0];
    }

    /**
     * Clear old screenshots (older than specified days)
     * @param daysOld Number of days to consider as old
     */
    public static void clearOldScreenshots(int daysOld) {
        File[] screenshots = getAllScreenshots();
        long cutoffTime = System.currentTimeMillis() - (long) daysOld * 24 * 60 * 60 * 1000;

        int deletedCount = 0;
        for (File screenshot : screenshots) {
            if (screenshot.lastModified() < cutoffTime) {
                if (screenshot.delete()) {
                    deletedCount++;
                    logger.debug("Deleted old screenshot: " + screenshot.getName());
                } else {
                    logger.warn("Failed to delete screenshot: " + screenshot.getName());
                }
            }
        }

        if (deletedCount > 0) {
            logger.info("Deleted " + deletedCount + " old screenshots");
        }
    }

}
