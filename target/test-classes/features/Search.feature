Feature: Price Checker

  Background: 
    Given The user is on amazon home page

  @search
  Scenario Outline: Searching book ISBN/Title
    When The user provides selected book ISBN/Title as "<rowNum>"
    And The user clicks on search button
    Then The user clicks on corresponding search result
    And The user gets book info
    And The user clicks on used book link
    Then The user gets list of prices
    Then File should be ready to send

    Examples: 
      | rowNum |
      |      0 |
      |      1 |
#|2|
#|3|
#|4|
