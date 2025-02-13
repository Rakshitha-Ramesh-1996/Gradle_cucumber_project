package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Remove static import of DriverManager.*, instead import the class:
import helper.DriverManager;

public class Helperbase {

    private static final Logger logger = LogManager.getLogger(Helperbase.class);

    // Logging info
    public void testStepInfo(String message) {
        logger.info(message);
    }

    // Logging failed test step
    public void testStepFailed(String message) {
        logger.error(message);
    }
    // Logging passed test step
    public void testStepPassed(String message) {
        logger.error(message);
    }

    // Enter text in a field
    public void enterText(String object, String text, String elemName) {
        try {
            WebElement we = getWebElement(object);
            if (we != null) {
                we.click();
                we.sendKeys(Keys.END);
                we.sendKeys(Keys.CONTROL + "a");
                we.sendKeys(Keys.DELETE);
                we.sendKeys(Keys.HOME);
                we.sendKeys(text);
                Thread.sleep(2000); // Consider replacing with explicit waits
                testStepInfo("Entered the value in the :" + elemName + " field :" + text);
            } else {
                testStepFailed("Element not found: " + elemName);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while entering the value in :" + elemName + " field, message is->" + ex.getMessage());
        }
    }

    // Click on an element
    public void clickOn(String obj, String elemName) {
        try {
            Thread.sleep(1000); // Temporary wait; consider replacing with explicit waits
            WebElement we = getWebElement(obj);
            if (we != null) {
                we.click();
                testStepInfo("Clicked on Element-" + elemName);
            } else {
                testStepFailed("Unable to find element to click: " + elemName);
            }
        } catch (Exception ex) {
            testStepFailed("Unable to Click on Element- " + elemName + " due to exception: " + ex.getMessage());
        }
    }

    // Get WebElement based on provided locator (xpath, css, or id)
    public WebElement getWebElement(String object) {
        try {
            // Retrieve the driver using the public method from DriverManager
            WebDriver driver = DriverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = null;
            if (object.startsWith("//")) {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
            } else if (object.startsWith("#")) {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(object)));
            } else {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(object))); // Default to ID
            }
            return element;
        } catch (Exception e) {
            logger.error("Error locating element: " + object + " -> " + e.getMessage());
            return null;
        }
    }
    public boolean verifyElementDisplayed(String elem, String elemName) {
        try {
            WebElement webElement = getWebElement(elem);
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement " + elemName + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement " + elemName + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elemName + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }
    public boolean isElementPresent(WebElement we) {
        try {
            return we.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    // Method to verify if a specific text is present
    public void verifyTextIsPresent(String expectedText, String elementXpath) {
        try {
            WebElement element = getWebElement(elementXpath);
            if (element != null) {
                String actualText = element.getText();
                // Assert if the text matches the expected text
                Assert.assertTrue("Text not found! Expected: " + expectedText + " but found: " + actualText,
                        actualText.contains(expectedText));
            } else {
                Assert.fail("Element with xpath " + elementXpath + " not found.");
            }
        } catch (Exception e) {
            Assert.fail("Exception caught while verifying text: " + e.getMessage());
        }
    }
}
