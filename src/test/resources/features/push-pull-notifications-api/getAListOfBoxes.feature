@feature
Feature: Push Pull Notifications API - Get A List Of Boxes

  ### GET - Get A List of Boxes Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @get-boxes @cmb @push-pull-notifications-api @regression-tests @imran2
  Scenario: Calling the get a list of boxes endpoint for a client ID which has boxes returns a status 200 OK response
    Given I have a valid JSON content type header
    #And I have a valid bearer token for scope "read:ppns-boxes" for my standard application using client credentials
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes box endpoint
    Then I get a successful response with default and CMBs displayed


  @get-boxes @cmb @push-pull-notifications-api @regression-tests
  Scenario: Calling the get a list of boxes endpoint with a client ID which has no boxes returns a 404 not found response
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my CMB application using client credentials
    When I make a request to the external get a list of boxes box endpoint
    Then I get a successful response with default and CMBs displayed

