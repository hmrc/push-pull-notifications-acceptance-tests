@feature
Feature: Push Pull Notifications API - Create A Client Managed Box

  ### [PUT] - Create A Client Managed Box Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with a new box name creates a new box
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated
    And I can delete the created client managed box by ID

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an existing box name updates the existing box
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with an existing box name
    Then the existing client managed box is successfully updated


  ### Accept Header Scenarios

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get a matching resource not found response

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header


  ### Content Type Header Scenarios

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have an invalid content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unsupported media type response

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with no content type header returns a status 415 unsupported media type response
    Given I have a valid JSON accept header
    And I have no content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unsupported media type response


  ### Authorization Scenarios

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid bearer token - non client credentials token
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes"
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unauthorised response due to an invalid bearer token

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an expired bearer token
    Given I have a valid JSON content type header
    When I make a request to the external create client managed box endpoint with an expired client credentials bearer token
    Then I get an unauthorised response due to invalid authentication information provided

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid scope returns a 401 unauthorised response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes"
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unauthorised response due to an invalid bearer token


  ### Request Body Scenarios

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid request body (invalid box name field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with an invalid box name field name
    Then I get a bad request response due to an invalid request payload

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid request body (no box name field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with no box name field name
    Then I get a bad request response due to an invalid request payload

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with an invalid request body (no box name value) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with no box name field value
    Then I get a bad request response due to missing box name

  @create-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the create client managed box endpoint with no request body returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with no request body
    Then I get a bad request response due to an invalid request payload
