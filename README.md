# Selenium Cucumber Project with Gradle and TestNG

This project is a sample Selenium test framework that uses Cucumber for behavior-driven development (BDD) and TestNG as the test runner. Gradle is used as the build tool and dependency manager. Additionally, Allure is integrated for generating test reports.

---

## Table of Contents

- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Allure Reporting](#allure-reporting)
- [Contributing](#contributing)
- [License](#license)

---

## Project Structure

```plaintext
Gradle_cucumber_project/
├── build.gradle
├── settings.gradle
├── README.md
├── libs/                    # (Optional) External JARs if needed
├── src/
│   ├── main/
│   │   ├── java/            # Application source code (if any)
│   │   └── resources/       # Application configuration files
│   └── test/
│       ├── java/
│       │   ├── stepDefinitions/  # Cucumber step definitions
│       │   ├── runners/          # Test runner classes (TestNG or JUnit)
│       │   │   └── TestRunner.java
│       │   └── utils/            # Utility classes (e.g., ConfigReader, ScreenshotUtils, VideoUtils)
│       └── resources/
│           ├── features/         # Cucumber feature files
│           │   └── *.feature
│           └── testng.xml        # TestNG configuration file
└── ...
```
## Prerequisites
Java JDK 8 or higher installed and configured in your system PATH.  
Gradle is required; you can use the Gradle Wrapper included in the project.  
Familiarity with Selenium WebDriver, Cucumber, and TestNG.  
(Optional) Allure Command Line for generating and viewing test reports.  

## Setup
Clone the Repository  
git clone https://github.com/your-username/Gradle_cucumber_project.git  
cd Gradle_cucumber_project  
Configure the Project  

All dependencies are managed via Gradle in the build.gradle file.  
TestNG is configured via the src/test/resources/testng.xml file (adjust it as needed).  
Import the Project into Your IDE  
Open IntelliJ IDEA (or your preferred IDE).  
Import the project as a Gradle project.  

## Running Tests
Using Gradle
Run All Tests:
./gradlew test
This command will compile the tests and run them using TestNG (configured via testng.xml).

Using TestNG XML Directly
You can also run tests by specifying the TestNG XML:
./gradlew test -Dtestng.xml=src/test/resources/testng.xml
## Allure Reporting
This project uses Allure to generate detailed test reports.

- 1. Install Allure Command Line (if not already installed)
On macOS:
brew install allure
On Windows/Linux:
Download from Allure Releases and add the bin directory to your PATH.
- 2. Generate Allure Report
After running your tests, generate the report with:
./gradlew allureReport
- 3. Serve the Allure Report
To view the report in your browser, run:
./gradlew allureServe
This will start a local server (usually on port 5123). Open your browser and go to:
http://localhost:5123

## Contributing
Contributions are welcome!
Please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
---

This README should provide a clear overview of the project, its folder structure, and in
