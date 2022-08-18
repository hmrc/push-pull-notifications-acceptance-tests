@feature
Feature: Push Pull Notifications API - Create Box

  ### [PUT] Create Box Endpoint

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an existing box works
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "PPNS Acceptance Test Box"
    Then A box is successfully generated

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with no request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with no request body
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an invalid request body (invalid field names) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with invalid field names
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with invalid request body (no field names and values) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with no field names and values
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an invalid request body (field names present with no values) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "" and Box Name ""
    Then I get a bad request response due missing parameter values

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with an invalid user agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get a forbidden response due to an invalid agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with no user agent header returns a 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get a forbidden response due to missing agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get an unsupported media type response

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get an unsupported media type response
