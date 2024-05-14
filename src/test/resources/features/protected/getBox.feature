@feature
Feature: Push Pull Notifications API - Get Boxes

  ### [GET] Get Boxes Endpoint

  ### Happy Path Scenarios

  @get-box @regression-tests
  Scenario: Calling the get box endpoint with no query parameters returns all PPNS boxes
    When I make a request to the Get Box endpoint with no query parameters
    Then I get a successful response where all boxes are returned

  @get-box @regression-tests
  Scenario: Calling the bet box with invalid clientId query parameter names returns a 400 bad request
    When I make a request to the Get Box endpoint with an invalid clientId query parameter name
    Then I get a bad request response due to invalid box name query parameter

  @get-box @regression-tests
  Scenario: Calling the get box endpoint with invalid boxName query parameter names returns a 400 bad request
    When I make a request to the Get Box endpoint with an invalid boxName query parameter name
    Then I get a bad request response due to invalid client ID query parameter

  @get-box @regression-tests
  Scenario: Calling the get box endpoint with no client ID value returns a 404 not found
    When I make a request to the Get Box endpoint with no client id parameter value
    Then I get a not found response due to box not found

  @get-box @regression-tests
  Scenario: Calling the get box endpoint with no box name value returns a 404 not found
    When I make a request to the Get Box endpoint with no box name parameter value
    Then I get a not found response due to box not found

