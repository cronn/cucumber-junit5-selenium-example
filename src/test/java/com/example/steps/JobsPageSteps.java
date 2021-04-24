package com.example.steps;

import com.example.pages.JobsPage;
import com.example.state.BrowserState;
import com.example.state.Properties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.PageFactory;

/**
 * Glue code that combines examples/scenarios with the PageObject for the jobs page.
 */
public class JobsPageSteps {

  private final BrowserState state;
  private final CookieSteps cookies;
  private final HeaderSteps header;
  private JobsPage jobsPage;

  public JobsPageSteps(final BrowserState state, CookieSteps cookies, HeaderSteps header) {
    this.state = state;
    this.cookies = cookies;
    this.header = header;
  }

  @Given("we open the jobs page")
  public void openPage() {
    jobsPage = PageFactory.initElements(state.driver(), JobsPage.class).get();
  }

  @When("we navigate to the jobs page")
  public void navigateToJobsPage() {
    if ("mobile".equals(Properties.device())) {
      cookies.allCookies();
    }
    jobsPage = header.navigateToJobsPage();
  }

  @Then("the page title can be seen")
  public void verifyTitle() {
    Assertions.assertTrue(jobsPage.isTitleVisible(), "title is not displayed on the jobs page");
  }

}
