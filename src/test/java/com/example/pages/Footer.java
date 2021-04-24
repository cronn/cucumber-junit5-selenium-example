package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object that represents just the footer that is shown on every page.
 */
public class Footer {

  @FindBy(xpath = "//p[contains(., 'Copyright cronn GmbH')]")
  private WebElement copyright;

  public boolean isCopyrightVisible() {
    return copyright.isDisplayed();
  }

}
