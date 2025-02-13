package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pages.Loginpage;
import static pages.Loginpage.*;

public class Login {
    Loginpage loginPage = new Loginpage();

    @Given("User is on login page")
    public void user_is_on_login_page() throws InterruptedException {
        launchUrl();
    }

    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterLoginDetails();
    }

    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() {
        boolean status = loginPage.verifyElementDashboardIsPresent();
        System.out.println(status);
    }


}
