@feature
Feature: Push Pull Notifications API - Client Managed Boxes

  ### (PUT) External Create CMB Box (currently using internal protected endpoint, will need to be refactored once RAML has been delivered)

  #@cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create client managed box endpoint
    Given  I have an internal bearer token for PPNS
    And I have a valid JSON content type header
    When I make a request to the create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated


  ### POST - Validate Client Managed Box Endpoint - Happy Path Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with a belonging box ID passes validation
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a validate true response

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with a non belonging box ID fails validation
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint for box ID "a5e3203d-a57e-4787-ba72-2dbfc294455f"
    Then I get a validate false response


  ### POST - Validate Client Managed Box Endpoint - Accept Header Scenarios

  #@cmb @push-pull-notifications-api @ppns @api-platform @regression-tests  - Need to update scenario so the reponse here is 406 {"code":"ACCEPT_HEADER_INVALID","message":"The accept header is missing or invalid"}
  Scenario: Calling the validate client managed box endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a matching resource not found response

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unacceptable response due to an invalid accept header

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unacceptable response due to a missing accept header


  ### POST - Validate Client Managed Box Endpoint - Content Type Header Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have an invalid content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unsupported media type response

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with no content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have no content type header
    When I make a request to the validate client managed box endpoint for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unsupported media type response


  ### POST - Validate Client Managed Box Endpoint - Request Body Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (invalid box ID field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with an invalid box ID field name
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (no box ID field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with no box ID field name
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (invalid box ID value) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with an invalid box ID value
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (no box ID value) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with no box ID value
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (invalid client ID field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with an invalid client ID field name
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (no client ID field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with no client ID field name
    Then I get a bad request response due to an invalid request payload

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with an invalid request body (no client ID value) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with no client ID value
    Then I get a bad request response due to missing box ID or client ID

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the validate client managed box endpoint with no request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the validate client managed box endpoint with no request body
    Then I get a bad request response due to an invalid request payload
