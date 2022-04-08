package com.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Page object that represents the landing page of cronn.de.
 *
 * @see <a href="https://cronn.de/">cronn.de</a>
 */
public class LandingPage extends LoadableComponent<LandingPage> {

  private final WebDriver driver;

  @FindBy(xpath = "//h1[contains(., 'wir entwickeln software_')]")
  private WebElement slogan;

  public LandingPage(final WebDriver driver) {
    this.driver = driver;
  }

  public boolean isSloganVisible() {
    return slogan.isDisplayed();
  }

  @Override
  protected void load() {
    driver.get("https://cronn.de/");
  }

  @Override
  protected void isLoaded() throws Error {
    final var url = driver.getCurrentUrl();
    Assertions.assertTrue(url.endsWith("cronn.de/"), "Not on the landing page: " + url);
  }

}
