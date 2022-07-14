@feature
Feature: Auth Login Stub

  @access_code @auth
  Scenario: Ensure we are able to generate an access code via the auth login stub frontend
    Given I am on the Auth login stub page
    When I complete the auth login stub form for scope "hello"
    Then I am able to grant an authority
    And an access code is successfully generated
