# Cucumber with JUnit5 and Selenium

This repository contains an example project that integrates [Cucumber](https://cucumber.io/) with [JUnit5](https://junit.org/junit5/) and [Selenium](https://www.selenium.dev/). It is the same setup explained in the [blog post](TODO). It is based on [cucumber-junit5-example](https://github.com/cronn/cucumber-junit5-example).

## Quick Start

```shell
$ git clone https://github.com/cronn/cucumber-junit5-selenium-example your-own-tests
$ cd your-own-tests
$ ./gradlew test
```

The [BrowserState](https://github.com/cronn/cucumber-junit5-selenium-example/blob/main/src/test/java/com/example/state/BrowserState.java) class is responsible for opening/closing new browser instances during test execution Additionally, it attaches a screenshot of the current browser viewport in case an example/scenario fails. It supports multiple environment variable in order to customize the browser for each test execution:

- `browser`: Defines which browser should be started. Allows `CHROME`, `CHROMIUM`, and `FIREFOX`. Defaults to `CHROME`.
- `lang`: The language used by the browser as a 2-letter country code. Defaults to `en`.
- `width`: The width of the browser window. Defaults to `1200`.
- `height`: The height of the browser window. Defaults to `1024`.
- `device`: If specified, overwrites `width` and `height` with the selected device preset. Allows `mobile`, `tablet`, and `desktop`.
- `timeout`: The default timeout which is used during page/script loading. Defaults to 15 (seconds).

Define them while calling Gradle like this:

```shell
$ ./gradlew test -Dbrowser=firefox -Dlang=de -Ddevice=tablet ...
```

The page interaction is handled by [page objects](https://www.selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/) which use the Selenium API to remote control a browser. The Cucumber step definitions are used to glue examples/scenarios to those page objects. We are testing the cronn homepage here, so you'll probably want to change that in your own tests, however you could keep the structure and general setup of state/steps/pages.

The GitHub [workflow](https://github.com/cronn/cucumber-junit5-selenium-example/blob/main/.github/workflows/gradle.yml#L29-L33) executes the same scenarios across multiple dimensions in a configuration matrix.

Note that this setup relies on browsers which are installed locally on your system. It will however automatically download the correct web driver for the browser you selected, thanks to the [WebDriverManager](https://github.com/bonigarcia/webdrivermanager). In case you want to use a different method to setup and initialize web drivers, change the implementation in `BrowserState` accordingly, e.g. by using [testcontainers](https://www.testcontainers.org/modules/webdriver_containers/).

[<img src="https://www.cronn.de/img/logo_name_rgb_1200x630.png" alt="cronn GmbH" width="200"/>](https://www.cronn.de/)
