@feature
Feature: Push Pull Notifications API - Get A List of Notifications

  ### [GET] Get A List of Notifications  - (API Gateway)

  ### Happy Path Scenarios

  ## Add test for when trying to get notifications for a box which does not belong do client id / creds

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint for pending status notifications works
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    And I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box
    When I make a request to the external get box notifications endpoint for pending status notifications
    Then I get a successful response with pending notifications

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with all valid query parameter value works
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a successful response with the correct notification details

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an unknown query parameter returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with unknown query parameters
    Then I get a bad request response due to invalid or unknown query parameters

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid status query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid status query parameter value
    Then I get an invalid request payload response due to an invalid status parameter provided

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid fromDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid fromDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid toDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid toDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided


  ### Accept Header Scenarios

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an incorrect accept header version
    Given I have an incorrect accept header version
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a matching resource not found response

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid accept header
    Given I have an invalid accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unacceptable response due to an invalid accept header

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with a missing accept header
    Given I have no accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unacceptable response due to a missing accept header


  ### Authorization Scenarios

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid scope
    Given I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a forbidden response due to invalid scope

  @get-notifications @push-pull-notifications-api @regression-tests
  Scenario: Calling the external get notifications endpoint with a non client credentials token
    Given I have a valid bearer token for scope "read:pull-notifications"
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unauthorised response due to an invalid bearer token
