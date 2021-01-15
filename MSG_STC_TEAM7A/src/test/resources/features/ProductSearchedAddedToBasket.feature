Feature: Product searched and added to the Basket
  
  As a user I want to search,  add a valid product to the basket
  and check  the basket 
  so that I can ensure product is available in the basket.

  @RegressionTest
  Scenario Outline: Verify that a product searched and added to the basket is available in the basket
    Given I am on RS_uk homepage
    When I search a "<valid_product>"
    And I add first product to the basket
    And I view the basket
    Then My basket page should show "<valid_product>"

    Examples: 
      | valid_product |
      | Motor         |
