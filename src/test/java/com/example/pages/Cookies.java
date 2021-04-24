package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object that represents just the cookie dialog that is shown on every page.
 */
public class Cookies {

  @FindBy(xpath = "//a[contains(., 'Alle akzeptieren')]")
  private WebElement allCookies;

  public void acceptAllCookies() {
    allCookies.click();
  }

}
