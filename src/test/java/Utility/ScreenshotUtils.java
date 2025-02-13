package Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("build/allure-results/screenshots/" + testName + ".png");
            FileUtils.copyFile(screenshot, destFile);

            // Attach the screenshot to the Allure report
            Allure.addAttachment(testName + " Screenshot", "image/png", screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
