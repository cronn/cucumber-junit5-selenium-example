@jobs
Feature: Jobs Page

  Scenario: The jobs title can be seen
    Given we open the landing page
    When we navigate to the jobs page
    Then the page title can be seen
