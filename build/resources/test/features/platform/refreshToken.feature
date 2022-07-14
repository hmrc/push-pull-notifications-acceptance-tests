@feature
Feature: Refresh Token

  @refresh-token @hello-world-api @api-platform @regression-tests
  Scenario: Refreshing a token generates a new token that can be used to call Hello User endpoint
    Given I have a valid bearer token for scope "hello"
    And I refresh the bearer token
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User

  @refresh-token @hello-world-api @api-platform @regression-tests
  Scenario: Refreshing tokens repeatedly generates a new token that can be used to call Hello User endpoint
    Given I have a valid bearer token for scope "hello"
    And I refresh the bearer token
    And I refresh the bearer token
    And I refresh the bearer token
    When I make a call to the get hello user endpoint
    Then I get a successful response with the message Hello User

  @refresh-token @hello-world-api @api-platform @regression-tests
  Scenario: Refreshing a token invalidates the previous bearer token
    Given I have a valid bearer token for scope "hello"
    And I refresh the bearer token
    But I use the old bearer token
    When I make a call to the get hello user endpoint
    Then I get an unauthorised response due to invalid credentials

  @refresh-token @hello-world-api @api-platform @regression-tests
  Scenario: Using a refresh token invalidates its use a second time
    Given I have a valid bearer token for scope "hello"
    And I refresh the bearer token
    When I refresh the bearer token with the used refresh token
    Then I get an invalid grant error
