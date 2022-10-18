@feature
Feature: Push Pull Notifications API - Get A List Of Boxes

  ### [GET] Get A List of Boxes Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint for a client ID which has boxes returns a status 200 OK response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get a successful response with default and client managed boxes displayed

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with a client ID which has no boxes returns a 404 not found response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my no boxes application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get a successful response with no boxes displayed


  ### Accept Header Scenarios

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with an incorrect accept header version returns a matching resource not found response
    Given I have an incorrect accept header version
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get a matching resource not found response

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with an invalid accept header returns a status 406 unacceptable response
    Given I have an invalid accept header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get an unacceptable response due to an invalid accept header

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with no accept header returns a status 406 unacceptable response
    Given I have no accept header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get an unacceptable response due to an invalid accept header


  ### Authorization Scenarios

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with an invalid bearer token - non client credentials token
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes"
    When I make a request to the external get a list of boxes endpoint
    Then I get an unauthorised response due to an invalid bearer token

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes box endpoint with an expired bearer token
    Given I have a valid JSON content type header
    When I make a request to the external get a list of boxes endpoint with an expired client credentials bearer token
    Then I get an unauthorised response due to invalid authentication information provided

  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with an invalid scope returns a 401 unauthorised response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    When I make a request to the external get a list of boxes endpoint
    Then I get a forbidden response due to invalid scope
