Feature: Wikipedia search

  Scenario Outline: Search returns at least one result
    Given I am on the Wikipedia home page
    When I search for "<query>"
    Then at least one result is shown
    And the page title contains "<query>"

    Examples:
      | query                        |
      | Selenium WebDriver           |
      | TestNG                       |
      | Java (programming language)  |
      | OpenAI                       |
