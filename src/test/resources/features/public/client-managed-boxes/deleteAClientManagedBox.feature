@feature
Feature: Push Pull Notifications API - Delete A Client Managed Box

  ### [DELETE] Delete A Client Managed Box Endpoint

  ### Happy Path Scenarios

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint for a created CMB returns a status 204 no content response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated
    And I can delete the created client managed box by ID

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an ID that does not exist returns a 404 not found response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a call to the delete client managed box endpoint with a box ID that does not exist
    Then I get a not found response due to box not found

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with a non ownership box ID returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a call to the delete client managed box endpoint with a non ownership box ID
    Then I get a forbidden response

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with a default box ID returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a call to the delete client managed box endpoint with a default box ID
    Then I get a forbidden response


  ### Accept Header Scenarios

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get a matching resource not found response

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unacceptable response due to an invalid accept header


  ### Authorization Scenarios

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an invalid bearer token - non client credentials token
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes"
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get an unauthorised response due to an invalid bearer token

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an expired bearer token
    Given I have a valid JSON content type header
    When I make a request to the external delete client managed box endpoint with an expired client credentials bearer token
    Then I get an unauthorised response due to invalid authentication information provided

  @delete-cmb @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the delete client managed box endpoint with an invalid scope returns a 403 forbidden response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external create client managed box endpoint with a new box name
    Then I get a forbidden response due to invalid scope
