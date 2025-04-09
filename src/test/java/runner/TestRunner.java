package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"}, // ✅ FIXED
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
//        dryRun = true ,// ✅ This checks if all steps have matching step definitions
        monochrome = true // ✅ Makes console output more readable

)
public class TestRunner {
}
