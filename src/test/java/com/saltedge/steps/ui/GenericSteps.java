package com.saltedge.steps.ui;

import com.saltedge.pom.pages.AccountsPage;
import com.saltedge.pom.pages.PaymentsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;

import static com.saltedge.helpers.AssertionWrapper.assertThatPageIsOpened;

public class GenericSteps {

    @Autowired
    private AccountsPage accountsPage;

    @Autowired
    private PaymentsPage paymentsPage;

    @Autowired(required = false)
    private WebDriver driver;

    @Value("#{'${accouunts-url}'}")
    private URL accountsUrl;

    @Value("#{'${payments-url}'}")
    private URL paymentsUrl;

    @Given("user navigates to the {} page")
    public void userNavigatesToTheAccountsPage(String page) {
        switch (page) {
            case "Accounts":
                driver.navigate().to(accountsUrl);
                //takeScreenshot(driver, "accounts");
                break;
            case "Payments":
                driver.navigate().to(paymentsUrl);
                //takeScreenshot(driver, "payments");
        }
    }

    @Then("user verifies all expected elements are present on the {}")
    public void userVerifiesAllExpectedElementsArePresent(String page) {
        switch (page) {
            case "Accounts":
                assertThatPageIsOpened(accountsPage);
                //takeScreenshot(driver, "accounts_page");
                break;
            case "Payments":
                assertThatPageIsOpened(paymentsPage);
                //takeScreenshot(driver, "payments_page");
        }
    }
}
