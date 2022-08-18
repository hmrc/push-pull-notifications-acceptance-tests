@feature
Feature: Push Pull Notifications API - Acknowledge A List Of Notifications

  ### [PUT] Acknowledge A List Of Notifications - (API Gateway)

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint works
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    And I have a notification in status pending for a new box
    And I have a valid JSON content type header
    When I make a request to the external put acknowledge notifications endpoint for the new box
    Then I get a successful response with acknowledged notifications

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint with an invalid accept header returns a 406 Not Acceptable
    Given I have an invalid accept header
    And I have a valid JSON content type header
    And I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    When I make a request to the external put acknowledge notifications endpoint
    Then I get an unacceptable response due to an invalid accept header

  #@push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests #test not working because un-set accept header
  #Scenario: Calling the external put acknowledge notifications endpoint with no accept header returns a 406 Not Acceptable
    #Given I have all valid request headers for PPNS
    #And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    #And I have a notification in status pending for a new box
    #And I have no accept header
    #And I have the new valid content type header
    #When I make a request to the external put acknowledge notifications endpoint
    #Then I get an unacceptable response due to a missing accept header

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have an invalid content type header
    And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    When I make a request to the external put acknowledge notifications endpoint
    Then I get an unsupported media type response version two

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint with no content type header returns a 415 unsupported media type
    Given I have no content type header
    And I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    When I make a request to the external put acknowledge notifications endpoint
    Then I get an unsupported media type response version two

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications with an invalid scope returns a 403 invalid scope
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    And I have a valid JSON content type header
    When I make a request to the external put acknowledge notifications endpoint
    Then I get a forbidden response due to invalid scope

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications with an invalid bearer token returns a 403 unauthorised
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "write:notifications"
    And I have a valid JSON content type header
    When I make a request to the external put acknowledge notifications endpoint
    Then I get an unauthorised response due to an invalid bearer token

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint for a non existing box returns 404 not found
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications write:notifications" for my standard application using client credentials
    And I have a notification in status pending for a new box
    And I have a valid JSON content type header
    When I make a request to the external put acknowledge notifications endpoint for a box that does not exist
    Then I get a not found response due to box not found

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external put acknowledge notifications endpoint with an invalid payload (no notification ID) returns a 400 bad request
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    And I have a valid JSON content type header
    When I make a request to the external put acknowledge notifications endpoint for a box that does not exist
    Then I get a bad request response due to an invalid request payload
