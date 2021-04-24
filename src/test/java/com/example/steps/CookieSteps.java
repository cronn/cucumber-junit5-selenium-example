package com.example.steps;

import com.example.pages.Cookies;
import com.example.state.BrowserState;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Glue code that combines examples/scenarios with the PageObject for the cookie dialog.
 */
public class CookieSteps {

  private final BrowserState state;

  public CookieSteps(final BrowserState state) {
    this.state = state;
  }

  @When("we accept all cookies")
  public void allCookies() {
    final var cookies = PageFactory.initElements(state.driver(), Cookies.class);
    cookies.acceptAllCookies();
  }

}
