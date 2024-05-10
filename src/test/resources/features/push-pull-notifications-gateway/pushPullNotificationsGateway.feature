@feature
Feature: Push Pull Notifications Gateway

  ### [POST] - Notify Endpoint

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint works
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get a successful response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an invalid host (non whitelisted) returns a 422 unprocessable entity response
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the notify endpoint with an invalid host
    Then I get an unprocessable entity response due to an invalid host

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an invalid payload (invalid field names) returns a 400 bad request
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the notify endpoint with invalid payload field names
    Then I get a bad request response due to an invalid request payload

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint works with an invalid payload (no field values)
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the notify endpoint with no payload field values
    Then I get a bad request response due to an invalid request payload

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with no payload returns a 400 bad request
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the notify endpoint with no payload
    Then I get a standard bad request response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an invalid authorization key returns a 403 forbidden
    Given  I have an invalid authorization key for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get a forbidden response due to an invalid or missing authorization key

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with no authorization key returns a 403 forbidden
    Given  I have no authorization key for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get a forbidden response due to an invalid or missing authorization key

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an invalid agent header returns a 403 forbidden response
    Given I have an invalid agent header for PPNS gateway
    And I have a valid content type header for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get a forbidden response due to an invalid agent header

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an no agent header returns a 403 forbidden response
    Given I have no user agent header for PPNS gateway
    And I have a valid content type header for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get a forbidden response due to an invalid agent header

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have an invalid content type header
    When I make a request to the notify endpoint with a valid payload
    Then I get an unsupported media type response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the push notifications endpoint with no content type header returns a 415 unsupported media type
    Given I have no content type header for PPNS gateway
    When I make a request to the notify endpoint with a valid payload
    Then I get an unsupported media type response


    #POST - Validate Callback URL Endpoint

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint works
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get a successful response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid host (non whitelisted) returns an invalid callback URL response
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the validate callback URL endpoint with an invalid host
    Then I get an invalid callback URL response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid payload (invalid field names) returns a 400 bad request
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the validate callback URL endpoint with an invalid payload
    Then I get a bad request response due to an invalid request payload

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid payload (no field values) returns an invalid callback URL response
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the validate callback URL endpoint with no payload field values
    Then I get an invalid callback URL response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with no payload returns a 400 bad request
    Given  I have all valid request headers for PPNS gateway
    When I make a request to the validate callback URL endpoint with no payload
    Then I get a standard bad request response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid authorization key returns a 403 forbidden
    Given  I have an invalid authorization key for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get a forbidden response due to an invalid or missing authorization key

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with no authorization key returns a 403 forbidden
    Given  I have no authorization key for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get a forbidden response due to an invalid or missing authorization key

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid agent header returns a 403 forbidden response
    Given I have an invalid agent header for PPNS gateway
    And I have a valid content type header for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get a forbidden response due to an invalid agent header

  #push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an no agent header returns a 403 forbidden response
    Given I have no user agent header for PPNS gateway
    And I have a valid content type header for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get a forbidden response due to an invalid agent header

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with an invalid content type header returns a 415 unsupported media type
    Given I have an invalid content type header
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get an unsupported media type response

  #@push-notifications @push-pull-notifications-gateway @regression-tests
  Scenario: Calling the validate callback URL endpoint with no content type header returns a 415 unsupported media type
    Given I have no content type header for PPNS gateway
    When I make a request to the validate callback URL endpoint with a valid payload
    Then I get an unsupported media type response
