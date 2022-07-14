@feature
Feature: Hello World API

  @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World endpoint works
    Given I have no authorisation header
    When I make a call to the get hello world endpoint
    Then I get a successful response with the message Hello World

  @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application endpoint with a valid server token works
    Given I have a valid server token
    When I make a call to the get hello application endpoint
    Then I get a successful response with the message Hello Application

  @hello-world-api @api-platform @regression-tests @imran
  Scenario: Calling Hello User endpoint with valid bearer token works
    Given I have a valid bearer token for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User
