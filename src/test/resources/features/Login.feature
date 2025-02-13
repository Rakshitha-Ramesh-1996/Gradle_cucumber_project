Feature: User Login

  Scenario: Successful login with valid credentials
    Given User is on login page
    When User enters valid username and password
    Then User should be redirected to the homepage