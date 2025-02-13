package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"}, // ✅ FIXED
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true // ✅ Makes console output more readable
)
public class TestRunner {
}










//package runner;
//
//import org.junit.platform.suite.api.ConfigurationParameter;
//import org.junit.platform.suite.api.SelectClasspathResource;
//import org.junit.platform.suite.api.Suite;
//import org.junit.platform.suite.api.SuiteDisplayName;
//import org.springframework.context.annotation.PropertySource;
//import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
//import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
//
//@Suite
//@SuiteDisplayName("Cucumber Test Suite")
//@SelectClasspathResource("features") // Your feature files directory
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions, hooks") // Your step definition packages
//@ConfigurationParameter(
//        key = PLUGIN_PROPERTY_NAME,
//        value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
//)
// // Allure plugin for Cucumber
//public class TestRunner {
//}
