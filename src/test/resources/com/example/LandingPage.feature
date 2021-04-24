@landing
Feature: Landing Page

  Scenario: The company slogan can be seen on the landing page
    When we open the landing page
    Then the company slogan can be seen
    And the copyright can be seen
