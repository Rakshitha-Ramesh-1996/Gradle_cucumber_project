package hooks;

import Utility.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.Loginpage;
import helper.DriverManager;
import Utility.VideoUtils;

public class Hooks {

    // Define the video path
    private static final String VIDEO_PATH = "build/allure-results/videos/test-video.mp4";

    // Start video recording before any scenario starts
    @Before
    public void beforeTest() {
        VideoUtils.startRecording(VIDEO_PATH);  // Start recording with the defined path
    }

    // Initialize the driver before each scenario
    @Before(order = 0)
    public void initializeDriver() throws InterruptedException {
        if (DriverManager.getDriver() == null) {  // Prevent re-initializing the driver
            Loginpage.launchUrl(); // Launch URL if driver is not initialized
        }
    }

    // Take a screenshot if the test fails
    @After
    public void tearDownFailure() {
        if (DriverManager.getDriver() != null) {
            ScreenshotUtils.takeScreenshot(DriverManager.getDriver(), "TestFailure");
        }
    }

    // Stop video recording after each scenario
    @After
    public void afterTest() {
        if (DriverManager.getDriver() != null) {
            VideoUtils.stopRecording(VIDEO_PATH);  // Stop recording and pass the video path
        }
    }

    // Quit the driver after all tests
    @After(order = 1)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }
    }
}
