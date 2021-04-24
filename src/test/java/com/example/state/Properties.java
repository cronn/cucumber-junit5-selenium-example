package com.example.state;

import java.util.Locale;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Utility class that accesses system properties.
 */
public class Properties {

  public static String language() {
    return System.getProperty("lang", "en").toLowerCase(Locale.ROOT);
  }

  public static String device() {
    return System.getProperty("device", "").toLowerCase(Locale.ROOT);
  }

  public static int width() {
    return Integer.getInteger("width", 1200);
  }

  public static int height() {
    return Integer.getInteger("height", 1024);
  }

  public static int timeout() {
    return Integer.getInteger("timeout", 15);
  }

  public static boolean headless() {
    return Boolean.parseBoolean(System.getProperty("headless", "true"));
  }

}
