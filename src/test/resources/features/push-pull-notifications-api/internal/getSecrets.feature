@feature
Feature: Push Pull Notifications API - Get Secret

  ### [GET] Get Secret Endpoint

  ### Happy Path Scenarios

  @get-secret @push-pull-notifications-api @regression-tests
  Scenario: Calling the secret endpoint with a valid authorization key works
    Given  I have a valid authorization key for the PPNS API
    When I make a request to the secrets endpoint
    Then I get a successful response with the correct secret returned

  @get-secret @push-pull-notifications-api @regression-tests
  Scenario: Calling the secret endpoint with an invalid authorization returns a 403 forbidden
    Given  I have an invalid authorization key for the PPNS API
    When I make a request to the secrets endpoint
    Then I get a forbidden response due to an invalid or missing authorization key
