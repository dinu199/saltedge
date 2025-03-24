package com.saltedge.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.saltedge.utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saltedge.config.DriverManager;
import java.util.Base64;

@Slf4j
public class Hooks {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver instanceof RemoteWebDriver && ((RemoteWebDriver) driver).getSessionId() == null) {
            throw new IllegalStateException("WebDriver session is not active!");
        }

        ExtentTest test = extent.createTest(scenario.getName());
        extentTest.set(test);
        log.info("Extent test started: {}", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        try {
            ExtentTest test = extentTest.get();

            if (scenario.isFailed()) {
                if (driver instanceof TakesScreenshot) {
                    try {
                        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                        String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

                        test.fail("Scenario Failed", MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot")
                                .build());

                        log.info("Screenshot successfully captured and attached.");
                    } catch (Exception screenshotEx) {
                        test.fail("Scenario Failed (Screenshot capture failed)");
                        log.error("Screenshot capture failed: {}", screenshotEx.getMessage());
                    }
                } else {
                    test.fail("Scenario Failed (Driver does not support screenshots)");
                }
            } else {
                test.pass("Scenario Passed");
            }

        } catch (Exception e) {
            log.error("Error while reporting to ExtentReports", e);
        } finally {
            if (driver != null) {
                log.info("Closing WebDriver...");
                driver.quit();
                DriverManager.quitDriver();
            }
            extent.flush();
            extentTest.remove();
        }
    }
}