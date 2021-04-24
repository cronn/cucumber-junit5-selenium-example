package com.example.steps;

import com.example.pages.Header;
import com.example.pages.JobsPage;
import com.example.state.BrowserState;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Glue code that combines examples/scenarios with the PageObject for the header.
 */
public class HeaderSteps {

  private final BrowserState state;

  public HeaderSteps(final BrowserState state) {
    this.state = state;
  }

  @When("we click on the jobs link")
  public JobsPage navigateToJobsPage() {
    final var header = PageFactory.initElements(state.driver(), Header.class);
    return header.clickOnJobs();
  }

}
