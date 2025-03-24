package com.saltedge.helpers;

import com.saltedge.pom.pages.Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public final class AssertionWrapper {

    public static void assertThatPageIsOpened(Page page) {
        try {
            assertThat(page.isAt()).as(page.getClass().getSimpleName() + " is not displayed").isTrue();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Page validation failed: " + e.getMessage(), e);
        }
    }

    public static void assertElementExists(WebElement element) {
        assertThat(element.isDisplayed()).as("Element should be present on page").isTrue();
    }

    public static void assertElementsAreEqual(double expected, double actual) {
        assertThat(actual)
                .as("Expected subtotal price (%.2f) should match calculated price (%.2f)", expected, actual)
                .isEqualTo(expected);
    }
}