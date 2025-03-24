package com.saltedge.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("ui")
public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null || (driver.get() instanceof RemoteWebDriver &&
                ((RemoteWebDriver) driver.get()).getSessionId() == null)) {
            log.info("Creating new WebDriver instance...");
            driver.set(createDriver());
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Quitting WebDriver...");
            driver.get().quit();
            driver.remove();
        }
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "edge").toLowerCase();
        String os = System.getProperty("os.name").toLowerCase();

        log.info("OS: {}", os);
        log.info("Selected browser: {}", browser);

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "safari":
                if (os.contains("mac")) {
                    return new SafariDriver();
                } else {
                    throw new UnsupportedOperationException("Safari is only available on macOS.");
                }
            case "edge":
            default:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
        }
    }
}