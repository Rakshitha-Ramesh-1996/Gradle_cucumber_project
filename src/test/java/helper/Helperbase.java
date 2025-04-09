package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    public void enterTextWoClick(String object, String text, String elemName) {
        try {
            WebElement we = getWebElement(object);
            if (we != null) {
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
    public List<WebElement> getWebElements(String object) {
        try {
            // Retrieve the driver using the public method from DriverManager
            WebDriver driver = DriverManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> elements = null;

            if (object.startsWith("//")) {
                // Locate all elements matching the XPath
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(object)));
            } else if (object.startsWith("#")) {
                // Locate all elements matching the CSS selector
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(object)));
            } else {
                // Default to ID if no specific selector type
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(object)));
            }

            return elements;
        } catch (Exception e) {
            logger.error("Error locating elements: " + object + " -> " + e.getMessage());
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

    public boolean verifyTextIsPresentBool(String expectedText, String elementXpath) {
        try {
            WebElement element = getWebElement(elementXpath);

            if (element != null) {
                String actualText = element.getText();

                // Check if the text contains the expected text
                if (actualText.contains(expectedText)) {
                    // Log pass message and assert pass
                    System.out.println("Text verification PASSED! Expected: " + expectedText + " was found in: " + actualText);
                    Assert.assertTrue("Text verification passed", true); // Pass assertion
                    return true;
                } else {
                    // Log failure if the expected text is not found
                    System.out.println("Text verification FAILED! Expected: " + expectedText + " but found: " + actualText);
                    Assert.fail("Text verification failed. Expected: " + expectedText + " but found: " + actualText); // Fail assertion
                    return false;
                }
            } else {
                // Log failure when the element is not found
                System.out.println("Element with xpath " + elementXpath + " not found.");
                Assert.fail("Element with xpath " + elementXpath + " not found.");
                return false;
            }
        } catch (Exception e) {
            // Log other exceptions
            System.out.println("Exception caught while verifying text: " + e.getMessage());
            Assert.fail("Exception caught while verifying text: " + e.getMessage());
            return false;
        }
    }

    public void selectRadioButtonForList(String obj, String value, String elemName) {
        try {
            Thread.sleep(1000); // Temporary wait; consider replacing with explicit waits

            // Find all radio buttons using the locator (assumes the locator is for a group of radio buttons)
            List<WebElement> radioButtons = getWebElements(obj);

            boolean buttonSelected = false;

            // Iterate through all radio buttons to find the one with the matching value
            for (WebElement radioButton : radioButtons) {
                String radioButtonValue = radioButton.getDomProperty("value"); // Get the value of the radio button
                if (radioButtonValue != null && radioButtonValue.equalsIgnoreCase(value)) {
                    if (!radioButton.isSelected()) {
                        radioButton.click(); // Select the radio button if it is not already selected
                        testStepInfo("Successfully selected the radio button: " + elemName + " with value: " + value);
                        buttonSelected = true;
                    } else {
                        testStepInfo("Radio button " + elemName + " with value: " + value + " is already selected.");
                        buttonSelected = true;
                    }
                    break;
                }
            }

            // If no radio button is selected
            if (!buttonSelected) {
                testStepFailed("Radio button with value: " + value + " not found in group: " + elemName);
            }
        } catch (Exception ex) {
            testStepFailed("Unable to select radio button: " + elemName + " with value: " + value + " due to exception: " + ex.getMessage());
        }
    }
    public void selectRadioButton(String obj, String value, String elemName) {
        try {
            Thread.sleep(1000); // Temporary wait; consider replacing with explicit waits

            // Find the radio button using the locator
            WebElement radioButton = getWebElement(obj);

            // Check if the radio button is found
            if (radioButton != null) {
                String radioButtonValue = radioButton.getDomAttribute("value");
                System.out.println(radioButtonValue);
                // If the radio button's value matches the passed value
                if (radioButtonValue != null && radioButtonValue.equalsIgnoreCase(value)) {
                    if (!radioButton.isSelected()) {
                        radioButton.click(); // Click to select the radio button if not already selected
                        testStepInfo("Successfully selected the radio button: " + elemName + " with value: " + value);
                    } else {
                        testStepInfo("Radio button " + elemName + " with value: " + value + " is already selected.");
                    }
                } else {
                    testStepFailed("Radio button with value: " + value + " not found in the element: " + elemName);
                }
            } else {
                testStepFailed("Radio button element: " + elemName + " not found.");
            }
        } catch (Exception ex) {
            testStepFailed("Unable to select radio button: " + elemName + " with value: " + value + " due to exception: " + ex.getMessage());
        }
    }

    public void clickAllCheckboxes(String locator, String elemName) {
        try {
            // Retrieve all checkbox elements using the provided locator
            List<WebElement> checkboxes = getWebElements(locator);

            if (checkboxes == null || checkboxes.isEmpty()) {
                testStepFailed("No checkboxes found for element: " + elemName);
                return;
            }

            // Iterate over each checkbox and click it if not already selected
            for (WebElement checkbox : checkboxes) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                    testStepInfo("Clicked checkbox for element: " + elemName);
                } else {
                    testStepInfo("Checkbox already selected for element: " + elemName);
                }
            }

            testStepInfo("Finished clicking all checkboxes for element: " + elemName);
        } catch (Exception e) {
            testStepFailed("Exception while clicking checkboxes for element '" + elemName + "': " + e.getMessage());
        }
    }
    public void selectFromDropdownByIndex(String selectLocator, int index, String dropdownName) {
        try {
            // Retrieve the dropdown WebElement using the provided locator
            WebElement dropdownElement = getWebElement(selectLocator);

            // Create a Select object for interacting with the dropdown
            Select dropdown = new Select(dropdownElement);

            // Select the option at the given index
            dropdown.selectByIndex(index);

            // Log success information
            testStepInfo("Selected the value with index " + index + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            // Log failure message along with the exception details
            testStepFailed("Unable to select the index " + index + " from dropdown " + dropdownName + ". Exception: " + ex.getMessage());
        }
    }
    public void selectFromDropdownByValue(String selectLocator, String value, String dropdownName) {
        try {
            // Retrieve the dropdown element using the locator provided
            WebElement dropdownElement = getWebElement(selectLocator);

            // Create a Select object for the dropdown
            Select dropdown = new Select(dropdownElement);

            // Select the option by the given value
            dropdown.selectByValue(value);

            // Log success information
            testStepInfo("Selected the value " + value + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            // Log failure message with the exception details
            testStepFailed("Unable to select the value " + value + " from dropdown " + dropdownName + ". Exception message is -> " + ex.getMessage());
        }
    }
    public void selectAndValidateDropdownOption(String selectLocator, String value, String dropdownName) {
        try {
            // Retrieve the dropdown element using the provided locator
            WebElement dropdownElement = getWebElement(selectLocator);
            Select dropdown = new Select(dropdownElement);

            // Select the option using the provided value
            dropdown.selectByValue(value);
            testStepInfo("Selected the value " + value + " from the dropdown " + dropdownName);

            // Validate that the selected option matches the parameter value
            String selectedOption = dropdown.getFirstSelectedOption().getDomAttribute("value");
            if(selectedOption.equals(value)) {
                testStepInfo("Validation successful: Selected option (" + selectedOption + ") matches the expected value (" + value + ").");
            } else {
                testStepFailed("Validation failed: Expected value " + value + " but found " + selectedOption + " in dropdown " + dropdownName);
            }
        } catch (Exception ex) {
            testStepFailed("Unable to select or validate the value " + value + " from dropdown " + dropdownName + ". Exception message is -> " + ex.getMessage());
        }
    }

    public void selectAndValidateDropdownOptionByLoop(String selectLocator, String expectedValue, String dropdownName) {
        try {
            // Retrieve the dropdown element
            WebElement dropdownElement = getWebElement(selectLocator);
            Select dropdown = new Select(dropdownElement);

            // Retrieve all options from the dropdown
            List<WebElement> options = dropdown.getOptions();
            boolean optionFound = false;

            // Loop through each option and click the one that matches the expected value
            for (WebElement option : options) {
                String optionValue = option.getDomAttribute("value");
                if (optionValue != null && optionValue.equals(expectedValue)) {
                    option.click();
                    optionFound = true;
                    testStepInfo("Clicked on the dropdown option with value: " + expectedValue + " in " + dropdownName);
                    break;
                }
            }

            // If the expected option wasn't found, log an error
            if (!optionFound) {
                testStepFailed("Option with value: " + expectedValue + " not found in dropdown " + dropdownName);
                return;
            }

            // Validate that the selected option is as expected
            String selectedOption = dropdown.getFirstSelectedOption().getDomAttribute("value");
            if (selectedOption.equals(expectedValue)) {
                testStepInfo("Validation successful: Selected option (" + selectedOption + ") matches expected value (" + expectedValue + ") in " + dropdownName);
            } else {
                testStepFailed("Validation failed: Expected value " + expectedValue + " but found " + selectedOption + " in " + dropdownName);
            }
        } catch (Exception ex) {
            testStepFailed("Exception while selecting dropdown option in " + dropdownName + ": " + ex.getMessage());
        }
    }


}
