package com.example.state;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Uses Cucumber's Before/After hooks to setup a remote controlled browser. Inject an instance of this class into your
 * steps in order to interact with the browser.
 */
public class BrowserState {

  private static final String CHROME = "CHROME";
  private static final String CHROMIUM = "CHROMIUM";
  private static final String FIREFOX = "FIREFOX";
  private static final Set<String> SUPPORTED_BROWSERS = Set.of(CHROME, CHROMIUM, FIREFOX);
  private static final Map<String, Dimension> DEVICE_PRESETS = Map.of(
      "mobile", new Dimension(375, 812),
      "tablet", new Dimension(1024, 768),
      "desktop", new Dimension(1200, 1024)
  );

  private static boolean managerInitialized = false;

  private WebDriver driver;

  /**
   * Cucumber-JVM has no BeforeAll yet, so we simulate that with a static value. However without synchronization, this
   * will break parallel test execution.
   */
  @Before
  public void setupBrowser() {
    final String browserType = System.getProperty("browser", CHROME).toUpperCase(Locale.ROOT);
    final String browserLang = Properties.language();
    if (!managerInitialized) {
      if (SUPPORTED_BROWSERS.contains(browserType)) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserType)).setup();
        managerInitialized = true;
      } else {
        throw new IllegalArgumentException("The given browser type is not supported - use CHROME, CHROMIUM, or FIREFOX");
      }
    }
    if (CHROME.equals(browserType)) {
      setupChrome(browserLang);
    } else if (FIREFOX.equals(browserType)) {
      setupFirefox(browserLang);
    }
    setupWindowSize();
    setupTimeouts();
  }

  private void setupChrome(final String browserLang) {
    final var options = new ChromeOptions();
    options.setHeadless(Properties.headless());
    options.addArguments("--no-sandbox");
    options.addArguments("--lang=" + browserLang);
    final var prefs = Map.of("intl.accept_languages", browserLang);
    options.setExperimentalOption("prefs", prefs);
    driver = new ChromeDriver(options);
  }

  private void setupFirefox(final String browserLang) {
    final var options = new FirefoxOptions();
    options.setHeadless(Properties.headless());
    options.addPreference("intl.accept_languages", browserLang);
    driver = new FirefoxDriver(options);
  }

  private void setupWindowSize() {
    if (Properties.device().isBlank()) {
      final int width = Properties.width();
      final int height = Properties.height();
      driver.manage().window().setSize(new Dimension(width, height));
    } else {
      driver.manage().window().setSize(DEVICE_PRESETS.getOrDefault(Properties.device(),
          new Dimension(Properties.width(), Properties.height())));
    }
  }

  private void setupTimeouts() {
    final int timeout = Properties.timeout();
    driver.manage().timeouts()
        .pageLoadTimeout(Duration.ofSeconds(timeout))
        .setScriptTimeout(Duration.ofSeconds(timeout))
        .implicitlyWait(Duration.ofSeconds(timeout));
  }

  /**
   * We cleanup after each example/scenario, so that each test has their own pristine browser.
   */
  @After
  public void teardown(final Scenario scenario) {
    if (scenario.isFailed()) {
      final var screenshot = (TakesScreenshot) driver;
      final var data = screenshot.getScreenshotAs(OutputType.BYTES);
      scenario.attach(data, "image/png", scenario.getName());
    }
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  /**
   * @return The currently active driver.
   */
  public WebDriver driver() {
    return driver;
  }

}
