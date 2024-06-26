@feature
Feature: Push Pull Notifications API - Create Wrapped Notifications

  ### [POST] Create Wrapped Notifications Endpoint

  ### Happy Path Scenarios

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with a JSON notification returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a JSON notification
    Then A notification is successfully generated

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with a XML notification returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a XML notification
    Then A notification is successfully generated

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint without a confirmation URL returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint without a confirmation URL
    Then A notification is successfully generated

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with optional private headers returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with optional private headers
    Then A notification with a confirmation URL is successfully generated

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an empty optional private header returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an empty optional private header
    Then A notification with a confirmation URL is successfully generated

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with more than 5 private header returns 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with more than 5 optional private headers
    Then I get a bad request response due to request contains more than 5 headers

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications with a box that does not exist returns a 404 not found
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a box that does not exist
    Then I get a not found response due to box not found

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications with an invalid UUID returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid UUID
    Then I get a bad request response due to the box ID not being a valid UUID


  ### Content Type Header Scenarios

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the create wrapped notification endpoint with a JSON notification
    Then I get an unsupported media type response version two

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the create wrapped notification endpoint with a JSON notification
    Then I get an unsupported media type response version two


  ### Authorization Scenarios

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a JSON notification
    Then I get a forbidden response due to an invalid agent header

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with no agent header returns a 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a JSON notification
    Then I get a forbidden response due to missing agent header


  ### Request Body Scenarios

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an empty string confirmation URL returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an empty string confirmation URL
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid confirmation URL returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid confirmation URL
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid message version returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid message version
    Then I get a bad request response due to message version invalid

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid JSON payload
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with no JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no JSON payload
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no notification field name) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no notification field name
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no notification field value) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no notification field name
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (invalid notification content type header) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid notification content type header
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no version field name) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no version field name
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no version field value) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no version field vale
    Then I get a bad request response due to message version invalid

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no private header name field) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no private header name field
    Then I get a bad request response due to an invalid request payload

  @create-wrapped-notifications @notifications @regression-tests
  Scenario: Calling the create wrapped notifications endpoint with an invalid request body (no private header value field) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with no private header value field
    Then I get a bad request response due to an invalid request payload
