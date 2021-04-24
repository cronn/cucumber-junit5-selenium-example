package com.example.pages;

import com.example.state.Properties;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object that represents just the header that is shown on every page.
 */
public class Header {

  private final WebDriver driver;

  @FindBy(xpath = "//div[contains(@class, 'nav-item-title') and contains(text(), 'Jobs')]")
  private WebElement jobs;
  
  @FindBy(xpath = "//i[@class='fal fa-bars font-l']")
  private WebElement burgerMenu;

  public Header(final WebDriver driver) {
    this.driver = driver;
  }

  public JobsPage clickOnJobs() {
    if ("mobile".equals(Properties.device())) {
      burgerMenu.click();
    }
    new WebDriverWait(driver, Duration.ofSeconds(Properties.timeout()))
        .until(ExpectedConditions.elementToBeClickable(jobs))
        .click();
    return PageFactory.initElements(driver, JobsPage.class).get();
  }

}
