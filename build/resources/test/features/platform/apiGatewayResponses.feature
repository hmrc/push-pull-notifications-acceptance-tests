@feature
Feature: API Gateway Responses

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World with an invalid context returns a 404 matching resource not found
    Given I have no authorisation header
    When I make a call to the get hello world endpoint with an invalid context
    Then I get a matching resource not found response

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World with an invalid URI returns a 404 Invalid URI
    Given I have no authorisation header
    When I make a call to the incorrect get hello world endpoint
    Then I get a matching resource not found response

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World with an incorrect header version returns a 404 matching resource not found
    Given I have an incorrect accept header version
    When I make a call to the get hello world endpoint
    Then I get a matching resource not found response

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World with an invalid method returns a 405 method not allowed
    Given I have no authorisation header
    When I make an invalid method call to the get hello world endpoint
    Then I get a method not allowed response

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World endpoint without an accept header results in a 406 Not acceptable
    Given I have no accept header
    When I make a call to the get hello world endpoint
    Then I get an unacceptable response due to a missing accept header

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello World endpoint without an unrecognized accept header results in a 406 Not acceptable
    Given I have an invalid accept header
    When I make a call to the get hello world endpoint
    Then I get an unacceptable response due to an invalid accept header

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application endpoint with a invalid server token fails
    Given I have a invalid server token
    When I make a call to the get hello application endpoint
    Then I get an unauthorised response due to invalid credentials

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application endpoint without server token fails
    Given I have no server token
    When I make a call to the get hello application endpoint
    Then I get an unauthorised response due to missing credentials

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint with an invalid bearer token fails
    Given I have an invalid bearer token
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to invalid credentials

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint with an incorrect bearer token type fails
    Given I have a valid server token
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to an incorrect bearer token type

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint with valid bearer token but wrong scope fails
    Given I have a valid bearer token for scope "read:individual-benefits"
    When I make a call to the get hello user endpoint
    Then I get a forbidden response due to invalid scope

  @api-gateway-responses @marriage-allowance-api @api-platform @regression-tests
  Scenario: Calling the Marriage Allowance API with no subscription
    Given I have a valid JSON accept header for version "2.0"
    And I have a valid bearer token for scope "read:marriage-allowance"
    When I make a request to the get Marriage Allowance Status endpoint with UTR "6116471786"
    Then I get a resource forbidden response due to no API subscription

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application endpoint with a blocked server token works
    Given I have a valid server token for a blocked application
    When I make a call to the get hello application endpoint
    Then I get a resource forbidden response due to the application being blocked

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello User endpoint with valid bearer token using blocked application
    Given I have a valid bearer token using a blocked application for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a resource forbidden response due to the application being blocked

  #@api-gateway-responses @api-platform-test @api-platform @regression-tests #Awaitinng fix APIS-5498 #should this be moved to ApiPlatformTest.feature
  Scenario: Calling the api-platform-test API post postcode endpoint with no JSON payload returns a 503
    Given I have no JSON payload
    When I make a request to the post JSON endpoint
    Then I get a bad request response due to no JSON payload

  @api-gateway-responses @api-platform-test @api-platform @regression-tests #should this be moved to ApiPlatformTest.feature
  Scenario: Calling the api-platform-test API post XML endpoint with no XML payload returns a 503
    Given I have no XML payload
    When I make a request to the post XML endpoint
    Then I get a bad request response due to no XML payload

  @api-gateway-responses @api-platform-test @api-platform @regression-tests #should this be moved to ApiPlatformTest.feature
  Scenario: Unsupported media type response due to missing JSON content type header
    Given I have a JSON payload with no content type header
    When I make a request to the post JSON endpoint
    Then I get an unsupported media type response

  @api-gateway-responses @api-platform-test @api-platform @regression-tests #should this be moved to ApiPlatformTest.feature
  Scenario: Unsupported media type response due to missing XML content type header
    Given I have an XML payload with no content type header
    When I make a request to the post XML endpoint
    Then I get an XML unsupported media type response

  #@api-gateway-responses @api-platform-test @api-platform @regression-tests #Awaitinng fix APIS-5498 #should this be moved to ApiPlatformTest.feature
  Scenario: Bad request response with an invalid JSON payload
    Given I have an invalid JSON payload
    When I make a request to the post JSON endpoint
    Then I get a bad request response due to invalid JSON

  @api-gateway-responses @api-platform-test @api-platform @regression-tests #should this be moved to ApiPlatformTest.feature
  Scenario: Bad request response with an invalid XML payload
    Given I have an invalid XML payload
    When I make a request to the post XML endpoint
    Then I get a bad request response due to invalid XML

  @api-gateway-responses @whitelisted-ip @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application with with a whitelisted IP returns a status 200
    Given I have a true client IP header for a whitelisted IP
    And I have a valid server token for an IP whitelisted application
    When I make a call to the get hello application endpoint
    Then I get a successful response

  @api-gateway-responses @whitelisted-ip @hello-world-api @api-platform @regression-tests
  Scenario: Calling Hello Application with with an IP not whitelisted returns a 403 endpoint not available
    Given I have a true client IP header for an IP not whitelisted
    And I have a valid server token for an IP whitelisted application
    When I make a call to the get hello application endpoint
    Then I get a resource forbidden response due to IP not whitelisted

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Generate a bearer token using an authorisation header and calling Hello User
    Given I have a valid bearer token created with an authorisation header for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User

  @api-gateway-responses @hello-world-api @api-platform @regression-tests
  Scenario: Generate a bearer token using JSON payload and calling Hello User
    Given I have a valid bearer token using a JSON payload for scope "hello"
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User
