package com.example.pages;

import com.example.state.Properties;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Page object that represents the jobs page of cronn.de.
 * 
 * @see <a href="https://www.cronn.de/jobs/">cronn.de/jobs</a>
 */
public class JobsPage extends LoadableComponent<JobsPage> {

  private final WebDriver driver;

  @FindBy(xpath = "//h3[contains(., 'Jobs')]")
  private WebElement title;

  public JobsPage(final WebDriver driver) {
    this.driver = driver;
  }
  
  public boolean isTitleVisible() {
    return title.isDisplayed();
  }
  
  @Override
  protected void load() {
    final var lang = Properties.language();
    if ("en".equals(lang)) {
      driver.get("https://www.cronn.de/jobs/index-en");
    } else {
      driver.get("https://www.cronn.de/jobs/");
    }
  }

  @Override
  protected void isLoaded() throws Error {
    final var url = driver.getCurrentUrl();
    final var lang = Properties.language();
    if ("en".equals(lang)) {
      Assertions.assertTrue(url.endsWith("cronn.de/jobs/index-en"), "Not on the jobs page: " + url);
    } else {
      Assertions.assertTrue(url.endsWith("cronn.de/jobs/"), "Not on the jobs page: " + url);
    }
  }

}
