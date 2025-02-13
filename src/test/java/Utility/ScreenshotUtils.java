package Utility;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            // Take the screenshot and store it as a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define the destination file for the screenshot
            File destFile = new File("build/allure-results/screenshots/" + testName + ".png");

            // Copy the screenshot to the destination file
            FileUtils.copyFile(screenshot, destFile);

            // Attach the screenshot to the Allure report using the FileInputStream
            try (FileInputStream fis = new FileInputStream(destFile)) {
                Allure.addAttachment(testName + " Screenshot", "image/png", fis, "png");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
