package hooks;

import Utility.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.Loginpage;
import helper.DriverManager;
import Utility.VideoUtils;

public class Hooks {

    // Define the video path
    private static final String VIDEO_PATH = "build/allure-results/videos/test-video.mp4";

    // Start video recording before any scenario starts
    @Before
    public void beforeTest() {
        try {
            VideoUtils.startRecording(VIDEO_PATH);  // Start recording with the defined path
        } catch (Exception e) {
            System.err.println("Error starting video recording: " + e.getMessage());
        }
    }

    // Initialize the driver before each scenario
    @Before(order = 0)
    public void initializeDriver() throws InterruptedException {
        if (DriverManager.getDriver() == null) {  // Prevent re-initializing the driver
            try {
                Loginpage.launchUrl(); // Launch URL if driver is not initialized
            } catch (Exception e) {
                System.err.println("Error initializing driver: " + e.getMessage());
                throw e;  // Rethrow the exception if driver initialization fails
            }
        }
    }

    // Take a screenshot for each step
    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        try {
            if (DriverManager.getDriver() != null) {
                // Capture screenshot and attach to Allure report
                ScreenshotUtils.takeScreenshot(DriverManager.getDriver(), "StepScreenshot");
            }
        } catch (Exception e) {
            System.err.println("Error capturing screenshot after step: " + e.getMessage());
        }
    }

    // Take a screenshot if the test fails
    @After
    public void tearDownFailure() {
        try {
            if (DriverManager.getDriver() != null) {
                ScreenshotUtils.takeScreenshot(DriverManager.getDriver(), "TestFailure");
            }
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }

    // Stop video recording after each scenario
    @After
    public void afterTest() {
        try {
            if (DriverManager.getDriver() != null) {
                VideoUtils.stopRecording(VIDEO_PATH);  // Stop recording and pass the video path
            }
        } catch (Exception e) {
            System.err.println("Error stopping video recording: " + e.getMessage());
        }
    }

    // Quit the driver after all tests
    @After(order = 1)
    public void tearDown() {
        try {
            if (DriverManager.getDriver() != null) {
                DriverManager.quitDriver();
            }
        } catch (Exception e) {
            System.err.println("Error quitting driver: " + e.getMessage());
        }
    }
}
