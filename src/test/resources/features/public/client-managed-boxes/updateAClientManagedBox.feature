@feature
Feature: Push Pull Notifications API - Update A Client Managed Box With A Callback URL

  ### [PUT] Update A Client Managed Box With A Callback URL Endpoint

  ### Happy Path Scenarios

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with a valid callback URL updates the CMB successfully
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a validate callback URL true response

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with a no callback URL updates the CMB successfully
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with no callback URL for box ID "43755189-038a-4e14-8cc8-b377b0dbb349"
    Then I get a validate callback URL true response

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid callback URL fails validation
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with an invalid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a validate callback URL false response

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint for a default box with a valid callback URL updates the CMB successfully
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "e0284be5-9102-4af9-8575-529a45808239"
    Then I get a forbidden response

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with a box ID that belongs to another client ID returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "5589dd9a-40e9-4dec-bbe3-9d83f5102a2a"
    Then I get a forbidden response


  ### Accept Header Scenarios

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a matching resource not found response

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unacceptable response due to an invalid accept header

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unacceptable response due to an invalid accept header


  ### Content Type Header Scenarios

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have an invalid content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unsupported media type response

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with no content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have no content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unsupported media type response


  ### Authorization Scenarios

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid bearer token - non client credentials token
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes"
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get an unauthorised response due to an invalid bearer token

  @update-cmb @cmb @push-pull-notifications-api @regression-tests #(Expired token = 32b61a0150e231e38efeeb664c2a79a2)
  Scenario: Calling the create client managed box endpoint with an expired bearer token
    Given I have a valid JSON content type header
    When I make a request to the external update client managed box endpoint with an expired client credentials bearer token
    Then I get an unauthorised response due to invalid authentication information provided

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid scope returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with a valid callback URL for box ID "a2eb7c0a-4571-44ad-9cbc-8d5143c0af7f"
    Then I get a forbidden response due to invalid scope


  ### Request Body Scenarios

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid request body (invalid callback URL field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with an invalid callback URL field name
    Then I get a bad request response due to an invalid request payload

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with an invalid request body (no callback url field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with no callback URL field name
    Then I get a bad request response due to an invalid request payload

  @update-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the update client managed box endpoint with no request body returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external update client managed box endpoint with no request body
    Then I get a bad request response due to an invalid request payload
