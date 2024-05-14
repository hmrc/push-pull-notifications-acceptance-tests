@feature
Feature: Push Pull Notifications API - Update Callback URL

  ### [PUT] Update Callback URL

  @callback @regression-tests
  Scenario: Calling the callback endpoint with a correct URL passes validation
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with a correct URL
    Then I get a successful response with a successful true response message

  @callback @regression-tests
  Scenario: Calling the callback endpoint with an incorrect URL fails validation
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with an incorrect URL
    Then I get an invalid callback URL response

  @callback @regression-tests
  Scenario: Calling the callback endpoint for a box that does not exist returns a 404 not found
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint where no box exists
    Then I get a not found response due to box not found

  @callback @regression-tests
  Scenario: Calling the callback endpoint with an invalid request body (invalid field names) returns a 400 bad request
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with invalid field names
    Then I get a bad request response due to an invalid request payload

  @callback @regression-tests
  Scenario: Calling the callback endpoint works with an invalid request body (no field values)
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with no field values
    Then I get a bad request response due to missing client ID

  @callback @regression-tests
  Scenario: Calling the callback endpoint with no request body returns a 400 bad request
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with no request body
    Then I get a bad request response due to an invalid request payload

  @callback @regression-tests
  Scenario: Calling the callback endpoint with an invalid client ID returns a 401 Unauthorsied
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint with a different client ID
    Then I get an unauthorised response due to client ID mismatch

  @callback @regression-tests
  Scenario: Calling the callback endpoint with a box that does not exist returns a 404 not found
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint for a box that does not exist
    Then I get a not found response due to box not found

  @callback @regression-tests
  Scenario: Calling the callback endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the callback endpoint with a correct URL
    Then I get an unsupported media type response

  @callback @regression-tests
  Scenario: Calling the callback endpoint with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the callback endpoint with a correct URL
    Then I get an unsupported media type response

  @callback @regression-tests
  Scenario: Calling the callback endpoint with an invalid agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the callback endpoint with a correct URL
    Then I get a forbidden response due to an invalid agent header

  @callback @regression-tests
  Scenario: Calling the validate callback endpoint with no agent header returns 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the callback endpoint with a correct URL
    Then I get a forbidden response due to missing agent header
