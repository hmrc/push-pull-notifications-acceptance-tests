@feature
Feature: Client Credentials

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Token generated with default scope via CC and calling an application restricted endpoint that requires no scope works
    Given I have a valid bearer token with the default scope for my standard application using client credentials
    When I make a call to the get hello application endpoint
    Then I get a successful response with the message Hello Application

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Token generated with default scope via CC and calling an application restricted endpoint requiring scopes returns a 403
    Given I have a valid bearer token with the default scope for my standard application using client credentials
    When I make a valid request to the hello bruce endpoint
    Then I get a forbidden response due to invalid scope

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Token generated for default scope via CC and calling a user restricted endpoint returns a 401 invalid credentials
    Given I have a valid bearer token with the default scope for my standard application using client credentials
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to invalid credentials

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Token generated with a specific scope via CC and calling an application restricted endpoint that requires a scope works
    Given I have a valid bearer token for scope "yippie-kai-yay" for my standard application using client credentials
    When I make a valid request to the hello bruce endpoint
    Then I get a successful response with the correct client details

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Token generated with a specific scope via CC and calling an application restricted endpoint that requires no scope works
    Given I have a valid bearer token for scope "yippie-kai-yay" for my standard application using client credentials
    When I make a call to the get hello application endpoint
    Then I get a successful response with the message Hello Application

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Generating a standard bearer token and calling the api-platform-test API on the hello bruce endpoint returns a 401
    Given I have a valid bearer token for scope "yippie-kai-yay"
    When I make a valid request to the hello bruce endpoint
    Then I get an unauthorised response

  @client-credentials @ropc-application @api-platform-test @api-platform @regression-tests
  Scenario: Token generated for an ROPC application via CC and calling an application restricted endpoint that requires no scope works
    Given I have a valid bearer token for my ROPC application using client credentials
    When I make a call to the get hello application endpoint
    Then I get a successful response with the message Hello Application

  @client-credentials @ropc-application @api-platform-test @api-platform @regression-tests
  Scenario: Token generated for an ROPC application via CC and calling an application restricted endpoint that requires a scope works
    Given I have a valid bearer token for my ROPC application using client credentials
    When I make a valid request to the hello bruce endpoint
    Then I get a successful response with the correct ROPC client details

  @client-credentials @ropc-application @api-platform-test @api-platform @regression-tests
  Scenario: Token generated for an ROPC application via CC and calling a user restricted endpoint returns a 401 invalid credentials
    Given I have a valid bearer token for my ROPC application using client credentials
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to invalid credentials

  @client-credentials @ropc-application @api-platform-test @api-platform @regression-tests
  Scenario: Generating an ROPC bearer token (grant_type = password) and calling the api-platform-test API on the hello bruce endpoint returns a 401
    Given I have a valid bearer token for my ROPC application
    When I make a valid request to the hello bruce endpoint
    Then I get an unauthorised response

  @client-credentials @privileged-application @api-platform-test @api-platform @regression-tests
  Scenario: Generate a bearer token for a privileged application to call Hello Bruce endpoint
    Given I have a valid bearer token for my privileged application
    When I make a valid request to the hello bruce endpoint
    Then I get a successful response with the correct privileged client details

  @client-credentials @api-platform-test @api-platform @regression-tests
  Scenario: Unable to generate an ROPC token using a standard application
    Given I have a standard application
    Then I am unable to generate an ROPC bearer token for a standard application

  @client-credentials @privileged-application @api-platform-test @api-platform @regression-tests
  Scenario: Unable to generate an ROPC token using a privileged application
    Given I have a privileged application
    Then I am unable to generate an ROPC bearer token for a privileged application
