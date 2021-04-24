package com.example.steps;

import com.example.pages.Footer;
import com.example.pages.LandingPage;
import com.example.state.BrowserState;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;

/**
 * Glue code that combines examples/scenarios with the PageObject for the footer.
 */
public class FooterSteps {

  private final BrowserState state;

  public FooterSteps(final BrowserState state) {
    this.state = state;
  }

  @Then("the copyright can be seen")
  public void verifyCopyright() {
    final var footer = PageFactory.initElements(state.driver(), Footer.class);
    Assertions.assertTrue(footer.isCopyrightVisible(),
        "copyright is not displayed on the current page: " + state.driver().getCurrentUrl());
  }

}
