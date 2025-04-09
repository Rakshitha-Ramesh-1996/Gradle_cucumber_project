package pages;

import Utility.ConfigReader;
import helper.DriverManager;
import helper.Helperbase;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import Utility.ConfigReader.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

//@Component  // Mark as a Spring-managed Bean
public class Loginpage extends Helperbase {
    public String usernameXpath = ConfigReader.getProperty("userName");
    public String passwordXpath = ConfigReader.getProperty("password");
    public String loginButtonXpath = ConfigReader.getProperty("login_button");
    public String dashboard = ConfigReader.getProperty("dashboard");
    public String featureItemsHomePage = ConfigReader.getProperty("featureItems");
    public String signupOrLoginBtn = ConfigReader.getProperty("signup_login_btn");
    public String signInNameField = ConfigReader.getProperty("signInName");
    public String signInEmailField = ConfigReader.getProperty("signInEmail");
    public String signUpBtnField = ConfigReader.getProperty("signUpBtn");
    public String enterAccountInformationField = ConfigReader.getProperty("enterAccountInformationText");
    public String radioTitleBtnField = ConfigReader.getProperty("radioTitleBtn");
    public String passwordInputField = ConfigReader.getProperty("passwordInput");
    public String checkBoxes = ConfigReader.getProperty("checkboxes");
    public String firstNameField = ConfigReader.getProperty("first_name");
    public String lastNameField = ConfigReader.getProperty("last_name");
    public String addressField = ConfigReader.getProperty("address");
    public String dropDownField = ConfigReader.getProperty("dropDown");
    public String stateField = ConfigReader.getProperty("state");
    public String cityField = ConfigReader.getProperty("city");
    public String zipcodeField = ConfigReader.getProperty("zipcode");
    public String mobileNumberField = ConfigReader.getProperty("mobile_number");
    public String createAccount = ConfigReader.getProperty("create_account");
    public String continueBtn = ConfigReader.getProperty("continue");
    public String accountCreatedOrDeleted = ConfigReader.getProperty("account_created");
    public String deleteAccountBtn = ConfigReader.getProperty("delete_account_btn");
    public String loginUsername = ConfigReader.getProperty("login_username");
    public String logoutUser = ConfigReader.getProperty("logout_user");
    public String loginEmailField = ConfigReader.getProperty("loginEmail");
    public String loginButtonField= ConfigReader.getProperty("loginButton");
    public String loginPageField= ConfigReader.getProperty("loginPage");
    public String loginError= ConfigReader.getProperty("login_Error");
    public String signupError= ConfigReader.getProperty("signup_Error");

    // Launch the URL (you can call this once, for example, in a Cucumber hook)
    public static void launchUrl() throws InterruptedException {
        String url = ConfigReader.getProperty("app.url"); // Fetch url
        DriverManager.launchUrl(url);
        // Optional: add a wait or sleep if needed
        Thread.sleep(1000);
    }

    public void enterLoginDetails() {
        enterText(usernameXpath, "Admin", "Username");
        enterText(passwordXpath, "admin123", "Password");
        clickOn(loginButtonXpath, "Login button");
    }

    public boolean verifyElementDashboardIsPresent() {
        verifyTextIsPresent("Dashboard", dashboard);
        return verifyElementDisplayed(dashboard, "Dashboard");
    }

    public boolean verifyElementFeaturesItemsIsPresent() throws InterruptedException {
        if (verifyTextIsPresentBool("FEATURES ITEMS", featureItemsHomePage)) {
            clickOn(signupOrLoginBtn, "Signup/Login button");
            Thread.sleep(1000);
            return true;
        } else
            return false;
    }

    public void enterNameAndEmail(String name, String email) throws InterruptedException {
        enterText(signInNameField, name, "Name");
        enterText(signInEmailField, email, "Email id");
        clickOn(signUpBtnField, "Sign Up button");
        Thread.sleep(1000);

    }
    public void enterEmailAndPassword(String email, String password, String username) throws InterruptedException {
        enterText(loginEmailField, email, "Email id");
        enterText(passwordXpath, password, "Password");
        clickOn(loginButtonField, "Login button");
        Thread.sleep(1000);
        verifyTextIsPresent(username, loginUsername);
    }

    // Method to convert DataTable and fill the registration form
    public void fillRegistrationFormFromDataTable(DataTable dataTable) {
        verifyTextIsPresent("ENTER ACCOUNT INFORMATION", enterAccountInformationField);
        // Convert the DataTable into a single map since there are only two columns: Field and Value
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        // Now you can retrieve the values using the keys from the first column
        String title = data.get("Title");
        String password = data.get("Password");
        String dob = data.get("DOB");
        String firstName = data.get("First Name");
        String lastName = data.get("Last Name");
        String address = data.get("Address");
        String country = data.get("Country");
        String state = data.get("State");
        String city = data.get("City");
        String zipCode = data.get("ZipCode");
        String mobileNumber = data.get("Mobile Number");

        // Call the enterText method for each field
        selectRadioButtonForList(radioTitleBtnField, title, "Title");
        enterTextWoClick(passwordInputField, password, "Password");
//        enterText("dob", dob, "DOB");
        clickAllCheckboxes(checkBoxes, "All checkboxes");
        enterTextWoClick(firstNameField, firstName, "First Name");
        enterTextWoClick(lastNameField, lastName, "Last Name");
        enterTextWoClick(addressField, address, "Address");
        selectAndValidateDropdownOption(dropDownField, country, "Country");
        enterTextWoClick(stateField, state, "Country");
        enterTextWoClick(cityField, city, "State");
        enterTextWoClick(zipcodeField, zipCode, "City");
        enterTextWoClick(mobileNumberField, mobileNumber, "ZipCode");
        clickOn(createAccount, "Create Account button");
        verifyTextIsPresent("ACCOUNT CREATED!", accountCreatedOrDeleted);
        clickOn(continueBtn, "Continue button");
        verifyTextIsPresent(firstName, loginUsername);
    }

    public void clickOnDeleteButton() {
        clickOn(deleteAccountBtn, "Delete button");
        verifyTextIsPresent("ACCOUNT DELETED!", accountCreatedOrDeleted);
        clickOn(continueBtn, "Continue button");
    }
    public void enterInvalidEmailAndPassword(String email, String password) throws InterruptedException {
        enterText(loginEmailField, email, "Email id");
        enterText(passwordXpath, password, "Password");
        clickOn(loginButtonField, "Login button");
        Thread.sleep(1000);
    }
    public void validateErrorMessage(String message){
        verifyTextIsPresent(message, loginError);
    }
    public void clickOnLogout(){
        clickOn(logoutUser,"Logout button");
        verifyTextIsPresent("Login to your account", loginPageField);
    }
    public void validateErrorMessageForSignup(String message){
        verifyTextIsPresent(message, signupError);
    }
}



