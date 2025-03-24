package com.saltedge.utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);
    private static final long DEFAULT_TIMEOUT = 10;

    public static List<String> awaitForMissingElements(long timeoutInSeconds, WebElement... elements) {
        List<String> missingElements = new ArrayList<>();

        if (elements == null || elements.length == 0) {
            log.warn("No elements provided to wait for.");
            return missingElements;
        }

        try {
            Awaitility.await().ignoreExceptions()
                    .atMost(timeoutInSeconds, TimeUnit.SECONDS)
                    .until(() -> {
                        boolean allDisplayed = true;
                        missingElements.clear();

                        for (WebElement element : elements) {
                            try {
                                boolean isDisplayed = element.isDisplayed();
                                if (!isDisplayed) {
                                    missingElements.add(element.toString());
                                    log.warn("⏳ Element NOT displayed: {}", element);
                                }
                                allDisplayed = allDisplayed && isDisplayed;
                            } catch (NoSuchElementException | StaleElementReferenceException e) {
                                missingElements.add(element.toString());
                                log.warn("Element NOT found or stale: {}", element);
                                allDisplayed = false;
                            }
                        }
                        return allDisplayed;
                    });

        } catch (Exception e) {
            log.error("Timeout occurred while waiting for elements: {}", e.getMessage());
        }

        return missingElements;
    }

    /**
     * Default timeout version of awaitForMissingElements
     *
     * @param elements elements to check
     * @return list of missing elements
     */
    public static List<String> awaitForMissingElements(WebElement... elements) {
        return awaitForMissingElements(DEFAULT_TIMEOUT, elements);
    }

    /**
     * Waits for a locator to appear in the DOM.
     *
     * @param driver           WebDriver instance
     * @param locator          By locator to find elements
     * @param timeoutInSeconds timeout in seconds
     * @return true if elements are found, false otherwise
     */
    public static boolean awaitForElements(WebDriver driver, By locator, long timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException e) {
            log.error("Timeout occurred while waiting for elements by locator {}: {}", locator, e.getMessage());
            return false;
        }
    }

    /**
     * Generic wait method for delaying execution.
     *
     * @param seconds number of seconds to wait
     */
    public static void waitFor(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted during wait: {}", e.getMessage());
        }
    }
}