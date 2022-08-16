@feature
Feature: Push Pull Notifications API - Delete A Client Managed Box

  ### DELETE - Delete A Client Managed Box Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the delete client managed box endpoint for a created CMB returns a status 204 no content response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated
    And I can delete the created client managed box by ID

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran5
  Scenario: Calling the delete client managed box endpoint with an ID that does not exist returns a 404 not found response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a call to the delete client managed box endpoint with ID "ccc1a3e7-2b73-475a-a14c-1428ab3b46bc"
    Then I get a not found response due to box not found

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran5
  Scenario: Calling the delete client managed box endpoint with an ID that belongs to another client ID 404 not found response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a call to the delete client managed box endpoint with ID "a5e3203d-a57e-4787-ba72-2dbfc294455f"
    Then I get a not found response due to box not found

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran5
  Scenario: Calling the delete client managed box endpoint with a default box ID returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a call to the delete client managed box endpoint with ID "046ceee5-e43f-4159-b5ce-8df5f2b9d1e3"
    Then I get a forbidden response



  ### Accept Header Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran # This needs to be updated so a 406 is returned
  Scenario: Calling the create client managed box endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get a matching resource not found response

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the validate client managed box endpoint with an no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header


  ### Content Type Header Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an invalid content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have an invalid content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unsupported media type response

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with no content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have no content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unsupported media type response


  ### Authorization Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an invalid bearer token - non client credentials token
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes"
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unauthorised response due to an invalid bearer token

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran3
  Scenario: Calling the create client managed box endpoint with an expired bearer token
    Given I have a valid JSON content type header
    When I make a request to the external create client managed box endpoint with an expired client credentials bearer token
    Then I get an unauthorised response due to an invalid bearer token

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an invalid scope returns a 401 unauthorised response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes"
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unauthorised response due to an invalid bearer token
  ### Expired tokens = 49511e91f510b62619d9bffa2639a507, ba97d13f6c1d420c117580977971fd2e

