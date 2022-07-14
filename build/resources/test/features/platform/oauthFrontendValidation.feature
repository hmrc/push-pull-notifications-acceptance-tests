@feature
Feature: OAuth Frontend Validation

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate an access code with an invalid client ID
    Given I have an invalid client ID
    Then I am unable to generate an access code due to an invalid client ID

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate an access code with an invalid redirect URI
    Given I have an invalid redirect URI
    Then I am unable to generate an access code due to an invalid redirect URI

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate an access code with an invalid scope
    Given I have an invalid scope
    Then I am unable to generate an access code due to an invalid scope

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate an access code for a privileged application
    Given I have a privileged application
    Then I am unable to generate an access code for a privileged applications using the standard auth journey

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate a standard access code for an ROPC application
    Given I have a privileged application
    Then I am unable to generate an access code for an ROPC applications using the standard auth journey

  @oauth-frontend @api-platform @regression-tests
  Scenario: Unable to generate a standard access code when including the client secret in my redirect URL
    Given I have a standard application
    Then I am unable to generate an access code when including a client secret in the redirect URL
