package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.Loginpage;

import static pages.Loginpage.*;

public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    Loginpage loginPage = new Loginpage();

    @Given("User is on login page")
    public void user_is_on_login_page() throws InterruptedException {
        launchUrl();
    }

//    @When("User enters valid username and password")
//    public void user_enters_valid_username_and_password() {
//        loginPage.enterLoginDetails();
//    }
//
//    @Then("User should be redirected to the homepage")
//    public void user_should_be_redirected_to_the_homepage() {
//        boolean status = loginPage.verifyElementDashboardIsPresent();
//        System.out.println(status);
//    }

    @When("User verify that home page is visible successfully")
    public void user_verify_that_home_page_is_visible_successfully() throws InterruptedException {
        boolean status = loginPage.verifyElementFeaturesItemsIsPresent();
        System.out.println(status);
    }

    @Then("User enter name {string} and email {string}")
    public void user_enter_name_rakshitha_and_email_arvraksha12_gmail_com(String name, String email) throws InterruptedException {
       loginPage.enterNameAndEmail(name, email);
    }

    @Then("User fill the registration form with the following details:")
    public void user_fill_the_registration_form_with_the_following_details(DataTable dataTable) {
        loginPage.fillRegistrationFormFromDataTable(dataTable);
    }
    @Then("User enter email {string} password {string} and {string}")
    public void user_enter_email_password_and(String email, String password, String username) throws InterruptedException {
        loginPage.enterEmailAndPassword(email,password,username);
    }

    @Then("User delete created account")
    public void user_delete_created_account() {
        loginPage.clickOnDeleteButton();
    }

    @Then("User enter invalid email {string} and password {string}")
    public void user_enter_invalid_email_and_password(String email, String password) throws InterruptedException {
        loginPage.enterInvalidEmailAndPassword(email,password);
    }
    @Then("User validate error message {string}")
    public void user_validate_error_message(String message) {
        loginPage.validateErrorMessage(message);
    }
    @Then("User logout from the application")
    public void user_logout_from_the_application() {
        loginPage.clickOnLogout();
    }
    @Then("User validate error message {string} existing email")
    public void user_validate_error_message_existing_email(String message) {
        loginPage.validateErrorMessageForSignup(message);
    }



}
