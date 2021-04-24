package com.example.steps;

import com.example.pages.LandingPage;
import com.example.state.BrowserState;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;

/**
 * Glue code that combines examples/scenarios with the PageObject for the landing page.
 */
public class LandingPageSteps {

  private final BrowserState state;
  private LandingPage landingPage;

  public LandingPageSteps(final BrowserState state) {
    this.state = state;
  }

  @When("we open the landing page")
  public void openPage() {
    landingPage = PageFactory.initElements(state.driver(), LandingPage.class).get();
  }

  @Then("the company slogan can be seen")
  public void verifySlogan() {
    Assertions.assertTrue(landingPage.isSloganVisible(), "slogan is not displayed on the landing page");
  }

}
