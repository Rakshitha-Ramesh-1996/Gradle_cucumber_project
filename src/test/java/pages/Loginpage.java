package pages;

import Utility.ConfigReader;
import helper.DriverManager;
import helper.Helperbase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import Utility.ConfigReader.*;
import org.springframework.stereotype.Component;

//@Component  // Mark as a Spring-managed Bean
public class Loginpage extends Helperbase {
    public String usernameXpath = ConfigReader.getProperty("userName");
    public String passwordXpath = ConfigReader.getProperty("password");
    public String loginButtonXpath = ConfigReader.getProperty("login_button");
    public String dashboard = ConfigReader.getProperty("dashboard");

    // Launch the URL (you can call this once, for example, in a Cucumber hook)
    public static void launchUrl() throws InterruptedException {
        DriverManager.launchUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Optional: add a wait or sleep if needed
        Thread.sleep(2000);
    }

    public void enterLoginDetails() {
        enterText(usernameXpath, "Admin", "Username");
        enterText(passwordXpath, "admin123", "Password");
        clickOn(loginButtonXpath, "Login button");
    }
    public boolean verifyElementDashboardIsPresent(){
        verifyTextIsPresent("Dashboard",dashboard);
        return verifyElementDisplayed(dashboard,"Dashboard");
    }
}
