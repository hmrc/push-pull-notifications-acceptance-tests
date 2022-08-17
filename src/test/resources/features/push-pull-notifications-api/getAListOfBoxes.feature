@feature
Feature: Push Pull Notifications API - Get A List Of Boxes

  ### GET - Get A List of Boxes Endpoint - (API Gateway)

  ### Happy Path Scenarios

  @get-boxes @cmb @ppns-api @regression-tests
  Scenario: Calling the get client managed boxes endpoint works
    Given I have a valid JSON content type header
    And I have a valid bearer token for scope "read:ppns-boxes" for my standard application using client credentials
    When I make a request to the external get a list of boxes box endpoint
    Then I get a successful response

