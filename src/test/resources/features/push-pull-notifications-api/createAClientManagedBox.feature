@feature
Feature: Push Pull Notifications API - Create A Client Managed Box

  ### PUT - Create A Client Managed Box Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create client managed box endpoint with a new box name creates a new box
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an existing box name updates the existing box
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then the existing client managed box is successfully updated


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
    And I have an invalid content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unsupported media type response


  ### Request Body Scenarios

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran2
  Scenario: Calling the create client managed box endpoint with an invalid request body (invalid box name field name) returns a 400 bad request
    Given  I have a valid JSON accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external create client managed box endpoint with an invalid box name field name
    Then I get a bad request response due to an invalid request payload
