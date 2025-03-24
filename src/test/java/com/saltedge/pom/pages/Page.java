package com.saltedge.pom.pages;

import com.saltedge.utils.WaitUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Page {

    List<WebElement> get();

    default boolean isAt() {
        List<String> missingElements = WaitUtils.awaitForMissingElements(this.get().toArray(new WebElement[0]));

        if (!missingElements.isEmpty()) {
            throw new NoSuchElementException("The following elements are missing: " + String.join(", ", missingElements));
        }
        return true;
    }
}
