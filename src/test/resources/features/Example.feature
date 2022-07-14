@Example

Feature: Example Feature file using Cucumber

  @ZAP
  Scenario: User is a limited cost business that pays annually and should use the 16.5% flat rate
    Given I am on the Check your VAT flat rate service
    When I submit my VAT for goods under £1000 for the year
    Then I will be asked to use the 16.5% VAT flat rate

  Scenario: User is not a limited cost business that pays annually and should use the VAT flat rate
    Given I am on the Check your VAT flat rate service
    When I submit my VAT information for goods over £1000 for the year
    Then I will be asked to use the VAT flat rate

  Scenario: User is a limited cost business that pays quarterly and should use the 16.5% flat rate
    Given I am on the Check your VAT flat rate service
    When I submit my VAT information for goods under £250 for the quarter
    Then I will be asked to use the 16.5% VAT flat rate

  Scenario: User is not a limited cost business that pays quarterly and should use the VAT flat rate
    Given I am on the Check your VAT flat rate service
    When I submit my VAT information for goods for £250 for the quarter
    Then I will be asked to use the VAT flat rate

