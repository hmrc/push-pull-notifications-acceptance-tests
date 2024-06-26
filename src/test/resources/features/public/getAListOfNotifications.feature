@feature
Feature: Push Pull Notifications API - Get A List of Notifications

  ### [GET] Get A List of Notifications

  ### Happy Path Scenarios

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint for pending status notifications works
    Given I have all valid request headers for PPNS
    And I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint for pending status notifications
    Then I get a successful response with pending notifications

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint for acknowledged status notifications works
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    And I have a generated notification in an acknowledged status
    When I make a request to the external get box notifications endpoint for acknowledged status notifications
    Then I get a successful response with acknowledged notifications

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with valid date query parameter values for pending notifications works
    Given I have all valid request headers for PPNS
    And  I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint for "PENDING" notifications with valid date query parameter values
    Then I get a successful response with the correct notification details

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with valid date query parameter values for acknowledged notifications works
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    And I have a generated notification in an acknowledged status
    When I make a request to the external get box notifications endpoint for "ACKNOWLEDGED" notifications with valid date query parameter values
    Then I get a successful response with the correct acknowledged notification details


  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an unknown query parameter returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with unknown query parameters
    Then I get a bad request response due to invalid or unknown query parameters

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid status query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid status query parameter value
    Then I get an invalid request payload response due to an invalid status parameter provided

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid fromDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid fromDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid toDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid toDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with a box ID that belongs to another client ID returns a 403 forbidden response
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with a box ID that belongs to another client ID
    Then I get a forbidden response


  ### Accept Header Scenarios

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an incorrect accept header version
    Given I have an incorrect accept header version
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint
    Then I get a matching resource not found response

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid accept header
    Given I have an invalid accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint
    Then I get an unacceptable response due to an invalid accept header

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with a missing accept header
    Given I have no accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint
    Then I get an unacceptable response due to a missing accept header


  ### Authorization Scenarios

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid scope
    Given I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint
    Then I get a forbidden response due to invalid scope

  @get-notifications @notifications @regression-tests
  Scenario: Calling the external get notifications endpoint with a non client credentials token
    Given I have a valid bearer token for scope "read:pull-notifications"
    When I make a request to the external get box notifications endpoint
    Then I get an unauthorised response due to an invalid bearer token
