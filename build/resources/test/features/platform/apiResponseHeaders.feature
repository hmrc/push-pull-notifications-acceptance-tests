@feature
Feature: API Response Headers

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World endpoint returns the expected response headers
    Given I have no authorisation header
    When I make a call to the get hello world endpoint
    Then I get a successful response with the message Hello World
    And I do not see any unwanted request headers in the response

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint returns the expected response headers
    Given I have a valid bearer token for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User
    And I do not see any unwanted request headers in the response

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint with an invalid bearer token returns the expected response headers
    Given I have an invalid bearer token
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to invalid credentials
    And I do not see any unwanted request headers in the response

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World endpoint successfully does not return a WWW-Authenticate response header
    When I make a call to the get hello world endpoint
    Then I get a successful response with the message Hello World
    And the WWW-Authenticate response header is not returned

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application endpoint successfully does not return a WWW-Authenticate response header
    Given I have a valid server token
    When I make a call to the get hello application endpoint
    Then I get a successful response with the message Hello Application
    And the WWW-Authenticate response header is not returned

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint successfully does not return a WWW-Authenticate response header
    Given I have a valid bearer token for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User
    And the WWW-Authenticate response header is not returned

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Receiving a 401 due to missing credentials returns the WWW-Authenticate response header
    Given I have no authorisation header
    When I make a call to the get hello application endpoint
    Then I get an unauthorised response due to missing credentials
    And the WWW-Authenticate response header is returned

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Receiving a 401 due to invalid credentials returns the WWW-Authenticate response header
    Given I have a invalid server token
    When I make a call to the get hello application endpoint
    Then I get an unauthorised response due to invalid credentials
    And the WWW-Authenticate response header is returned

  @response-headers @hello-world-api @api-platform @regression-tests
  Scenario: Receiving a 401 due to incorrect bearer token type returns the WWW-Authenticate response header
    Given I have a valid server token
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to an incorrect bearer token type
    And the WWW-Authenticate response header is returned
