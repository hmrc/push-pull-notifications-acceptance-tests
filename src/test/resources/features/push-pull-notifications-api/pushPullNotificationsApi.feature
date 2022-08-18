@feature
Feature: Push Pull Notifications API

  ### End to end scenario - Includes internal and external endpoints

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests @imran5
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
    Then A notifications is successfully generated
    #Call the External Notifications endpoint and assert details
    When I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    And I make a request to the external get box notifications endpoint for the new box
    Then I get a successful response with the correct notification details for the new box


  ### (GET) External Box Notifications Endpoint - API Gateway

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint for pending status notifications works
    Given I have all valid request headers for PPNS
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    And I make a request to the create notifications endpoint to generate a pending notification for an unsubscribed box
    When I make a request to the external get box notifications endpoint for pending status notifications
    Then I get a successful response with pending notifications

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an unknown query parameter returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with unknown query parameters
    Then I get a bad request response due to invalid or unknown query parameters

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid status query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid status query parameter value
    Then I get an invalid request payload response due to an invalid status parameter provided

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid fromDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid fromDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid toDate query parameter value returns a 400 bad request
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with an invalid toDate query parameter value
    Then I get an invalid request payload response due to an unparsable date value parameter provided

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests @imran3
  Scenario: Calling the external get notifications endpoint with all valid query parameter value works
    Given I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a successful response with the correct notification details

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid scope
    Given I have a valid bearer token for scope "write:notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a forbidden response due to invalid scope

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with a non client credentials token
    Given I have a valid bearer token for scope "read:pull-notifications"
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unauthorised response due to an invalid bearer token

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an incorrect accept header version
    Given I have an incorrect accept header version
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get a matching resource not found response

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with an invalid accept header
    Given I have an invalid accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unacceptable response due to an invalid accept header

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the external get notifications endpoint with a missing accept header
    Given I have no accept header
    And I have a valid bearer token for scope "read:pull-notifications" for my standard application using client credentials
    When I make a request to the external get box notifications endpoint with all valid query parameter values
    Then I get an unacceptable response due to a missing accept header


  ### (PUT) External Acknowledge Notifications Endpoint - API Gateway

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


  ### (PUT) Create Box Endpoint

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an existing box works
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "PPNS Acceptance Test Box"
    Then A box is successfully generated

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with no request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with no request body
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an invalid request body (invalid field names) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with invalid field names
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with invalid request body (no field names and values) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create box endpoint with no field names and values
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint with an invalid request body (field names present with no values) returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "" and Box Name ""
    Then I get a bad request response due missing parameter values

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with an invalid user agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get a forbidden response due to an invalid agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with no user agent header returns a 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get a forbidden response due to missing agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get an unsupported media type response

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create box endpoint works with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the create Box endpoint with Client ID "1AgmuykNGEm84u4xMExNKgZB6uqd" and Box Name "API Platform Acceptance Test Box"
    Then I get an unsupported media type response


  ### (GET) Box Endpoint

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the get box endpoint with no query parameters returns all PPNS boxes
    When I make a request to the Get Box endpoint with no query parameters
    Then I get a successful response where all boxes are returned

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the bet box with invalid clientId query parameter names returns a 400 bad request
    When I make a request to the Get Box endpoint with an invalid clientId query parameter name
    Then I get a bad request response due to invalid box name query parameter

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the get box endpoint with invalid boxName query parameter names returns a 400 bad request
    When I make a request to the Get Box endpoint with an invalid boxName query parameter name
    Then I get a bad request response due to invalid client ID query parameter

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the get box endpoint with no client ID value returns a 404 not found
    When I make a request to the Get Box endpoint with no client id parameter value
    Then I get a not found response due to box not found

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the get box endpoint with no box name value returns a 404 not found
    When I make a request to the Get Box endpoint with no box name parameter value
    Then I get a not found response due to box not found


  ### (PUT) Validate Callback URL

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with a correct URL passes validation
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with a correct URL
    Then I get a successful response with a successful true response message

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with an incorrect URL fails validation
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with an incorrect URL
    Then I get an invalid callback URL response

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the callback endpoint for a box that does not exist returns a 404 not found
    Given I have all valid request headers for PPNS
    When I make a request to the callback endpoint where no box exists
    Then I get a not found response due to box not found

  @push-pull-notifications-gateway @ppns @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with an invalid request body (invalid field names) returns a 400 bad request
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with invalid field names
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-gateway @ppns @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint works with an invalid request body (no field values)
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with no field values
    Then I get a bad request response due to missing client ID

  @push-pull-notifications-gateway @ppns @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with no request body returns a 400 bad request
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with no request body
    Then I get a bad request response due to an invalid request payload

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with an invalid client ID returns a 401 Unauthorsied
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint with a different client ID
    Then I get an unauthorised response due to client ID mismatch

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with a box that does not exist returns a 404 not found
    Given I have all valid request headers for PPNS
    When I make a request to the validate callback endpoint for a box that does not exist
    Then I get a not found response due to box not found

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the validate callback endpoint with a correct URL
    Then I get an unsupported media type response

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the validate callback endpoint with a correct URL
    Then I get an unsupported media type response

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with an invalid agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the validate callback endpoint with a correct URL
    Then I get a forbidden response due to an invalid agent header

  @push-pull-notifications-api @ppns @external-endpoint @api-platform @regression-tests
  Scenario: Calling the validate callback endpoint with no agent header returns 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the validate callback endpoint with a correct URL
    Then I get a forbidden response due to missing agent header


  ### (POST) Create Notfications Endpoint

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with an invalid JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with no JSON request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with no JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with a valid XML request body works
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with a valid XML payload
    Then A notifications is successfully generated

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid XML request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with an invalid XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with no XML request body returns a 400 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with no XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with a valid XML content type header and JSON request body returns a 404 bad request
    Given I have a valid user agent header
    And I have a valid XML content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications with a valid JSON content type header and XML request body returns a 404 bad request
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with a valid XML payload
    Then I get a bad request response due to content type not supported or message syntax invalid

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications with a valid UUID box that does not exist returns a 404 not found
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with an valid UUID box that does not exist
    Then I get a not found response due to box not found

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications with a invalid UUID box that does not exist returns a 404 not found
    Given I have a valid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with an invalid UUID box that does not exist
    Then I get a bad request response due to the box ID not being a valid UUID

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid agent header returns a 403 forbidden
    Given I have an invalid user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get a forbidden response due to an invalid agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with no agent header returns a 403 forbidden
    Given I have no user agent header
    And I have a valid JSON content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get a forbidden response due to missing agent header

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have an invalid content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get an unsupported media type response due to content type not supported

  @push-pull-notifications-api @ppns @api-platform @regression-tests
  Scenario: Calling the create notifications endpoint with no content type header returns a 415 unsupported media type
    Given I have a valid user agent header
    And I have no content type header
    When I make a request to the create notification endpoint with a valid JSON payload
    Then I get an unsupported media type response due to content type not supported


  ### (GET) Secrets Endpoint

  @push-pull-notifications-gateway @ppns @api-platform @regression-tests
  Scenario: Calling the secret endpoint with a valid authorization key works
    Given  I have a valid authorization key for the PPNS API
    When I make a request to the secrets endpoint
    Then I get a successful response with the correct secret returned

  @push-pull-notifications-gateway @ppns @api-platform @regression-tests
  Scenario: Calling the secret endpoint with an invalid authorization returns a 403 forbidden
    Given  I have an invalid authorization key for the PPNS API
    When I make a request to the secrets endpoint
    Then I get a forbidden response due to an invalid or missing authorization key
