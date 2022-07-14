@feature
Feature: Root Endpoints Testing

  @root-endpoints @api-platform-test @api-platform @regression-tests
  Scenario: Calling the api-platform-test API root endpoint works for version 2.0
    Given I have no authorisation header
    When I make a valid request to the root endpoint on version 2.0
    Then I get a response with method "GET" and path "/" and details "Ciao Empty!"

  @root-endpoints @api-platform-test @api-platform @regression-tests
  Scenario: Calling the api-platform-test API root endpoint with a trailing slash works for version 2.0
    Given I have no authorisation header
    When I make a valid request to the root endpoint with a trailing slash on version 2.0
    Then I get a response with method "GET" and path "/" and details "Ciao Empty!"

  @root-endpoints @individual-benefits-api @api-platform @regression-tests
  Scenario: Calling the Individual Benefits API root endpoint
    Given I have a valid bearer token for scope "read:individual-benefits"
    And I have a valid JSON accept header for version "1.1"
    When I make a request to the get benefits summary root endpoint
    Then I get a matching resource not found response

  @root-endpoints @individual-benefits-api @api-platform @regression-tests
  Scenario: Calling the Individual Benefits API root endpoint with a trailing slash
    Given I have a valid bearer token for scope "read:individual-benefits"
    And I have a valid JSON accept header for version "1.1"
    When I make a request to the get benefits summary root endpoint with a trailing slash
    Then I get a matching resource not found response

  @root-endpoints @individual-income-api @api-platform @regression-tests
  Scenario: Calling the Individual Income API root endpoint
    Given I have a valid bearer token for scope "read:individual-income"
    And I have a valid JSON accept header for version "1.1"
    When I make a request to the get income summary root endpoint
    Then I get a matching resource not found response
