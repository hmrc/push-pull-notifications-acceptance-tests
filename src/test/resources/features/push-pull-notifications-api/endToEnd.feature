@feature
Feature: Push Pull Notifications API - End To End Journey

  ### End to end scenario - Includes internal and external endpoints

  @end-to-end @push-pull-notifications-api @regression-tests
  Scenario: PPNS end to end test
    Given I have all valid request headers for PPNS
    When I make a new request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and a new box name
    Then A new box is successfully generated
    #Assert Box is Created
    When I make a request to the Get Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and new box name
    Then the new box is successfully returned
    #Set Callback URL
    When I make a request to the callback endpoint using the new box
    Then I can set a callback url
    #Create a Notification in JSON
    When I make a request to the create notification endpoint with a valid JSON payload for the new box
    Then A notification is successfully generated
    #Call the External Notifications endpoint and assert details
    When I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    And I make a request to the external get box notifications endpoint for the new box
    Then I get a successful response with the correct notification details for the new box
