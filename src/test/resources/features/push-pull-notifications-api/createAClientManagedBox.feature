@feature
Feature: Push Pull Notifications API - Create A Client Managed Box

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create client managed box endpoint with a new box name creates a new box
    Given I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    And I have a valid JSON content type header
    When I make a request to the external create client managed box endpoint with a new box name
    Then A new client managed box is successfully generated

  @cmb @push-pull-notifications-api @ppns @api-platform @regression-tests @imran
  Scenario: Calling the create client managed box endpoint with an existing box name updates the existing box
    Given I have a valid bearer token for scope "write:ppns-boxes" for my standard application using client credentials
    And I have a valid JSON content type header
    When I make a request to the external create client managed box endpoint with a new box name
    Then the existing client managed box is successfully updated
