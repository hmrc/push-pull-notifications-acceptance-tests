@feature
Feature: Push Pull Notifications API - Create Wrapped Notifications

  ### [POST] Create Wrapped Notifications Endpoint

  ### Happy Path Scenarios

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications endpoint with a valid JSON request body returns a successful 201 response
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a valid JSON payload
    Then A notifications is successfully generated

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications endpoint with an invalid message version returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid message version
    Then I get a bad request response due to message version invalid

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications with a box that does not exist returns a 404 not found
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a box that does not exist
    Then I get a not found response due to box not found

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications with an invalid UUID returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with an invalid UUID
    Then I get a bad request response due to the box ID not being a valid UUID


  ### Content Type Header Scenarios

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the create wrapped notification endpoint with a valid JSON payload
    Then I get an unsupported media type response version two

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create notifications endpoint with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the create wrapped notification endpoint with a valid JSON payload
    Then I get an unsupported media type response version two


  ### Authorization Scenarios

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create wrapped notifications endpoint with an invalid agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a valid JSON payload
    Then I get a forbidden response due to an invalid agent header

  @create-notifications @push-pull-notifications-api @regression-tests @wrapped
  Scenario: Calling the create notifications endpoint with no agent header returns a 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the create wrapped notification endpoint with a valid JSON payload
    Then I get a forbidden response due to missing agent header


  ### Request Body Scenarios

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with an invalid JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications endpoint with no JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with no JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid XML request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with an invalid XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications endpoint with no XML request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with no XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications endpoint with a valid XML content type header and JSON request body returns a 404 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @create-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the create notifications with a valid JSON content type header and XML request body returns a 404 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with a valid XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid
