Feature: User Login

#  Scenario: Successful login with valid credentials
#    Given User is on login page
#    When User enters valid username and password
#    Then User should be redirected to the homepage

  Scenario Outline: Test Case 1: Register User
    Given User is on login page
    When User verify that home page is visible successfully
    Then User enter name "<name>" and email "<email>"
    And User fill the registration form with the following details:
      | Field         | Value        |
      | Title         | Mrs          |
      | Password      | Pass@123     |
      | DOB           | 12-08-2001   |
      | First Name    | Rakshitha    |
      | Last Name     | Ramesh       |
      | Address       | Yellamandala |
      | Country       | India        |
      | State         | karnataka    |
      | City          | Tumkur       |
      | ZipCode       | 572103       |
      | Mobile Number | 9874364676   |
    Then User delete created account
    Examples:
      | name      | email                    |
      | Rakshitha | rakshaRamesh12@gmail.com |

  Scenario Outline: Test Case 2: Login User with correct email and password
    Given User is on login page
    When User verify that home page is visible successfully
    Then User enter email "<email>" password "<password>" and "<username>"
    Then User delete created account
    Examples:
      | email                 | password | username  |
      | arvraksha12@gmail.com | Pass@123 | Rakshitha |

  Scenario Outline: Test Case 3: Login User with incorrect email and password
    Given User is on login page
    When User verify that home page is visible successfully
    Then User enter invalid email "<email>" and password "<password>"
    Then User validate error message "<message>"
    Examples:
      | email             | password | message                              |
      | 1raksha@gmail.com | Test123  | Your email or password is incorrect! |

  Scenario Outline: Test Case 4: Logout User
    Given User is on login page
    When User verify that home page is visible successfully
    Then User enter email "<email>" password "<password>" and "<username>"
    Then User logout from the application
    Examples:
      | email              | password  | username |
      | arvind12@gmail.com | Pass@1234 | Arvind   |

  Scenario Outline: Test Case 5: Register User with existing email
    Given User is on login page
    When User verify that home page is visible successfully
    Then User enter name "<name>" and email "<email>"
    Then User validate error message "<message>" existing email
    Examples:
      | name  | email                    | message                      |
      | Vamsi | vamsikrishna12@gmail.com | Email Address already exist! |