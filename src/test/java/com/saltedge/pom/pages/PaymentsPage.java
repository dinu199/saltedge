package com.saltedge.pom.pages;

import com.saltedge.pom.blocks.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentsPage extends BasePage implements Page {

    @Autowired
    private Header header;

    @FindBy(xpath = "//h1[@id='payments']")
    private WebElement paymentsLabel;

    @FindBy(css = "[href='#bulk-payments'][class='next-link']")
    private WebElement cardAccountsSelectorButton;

    @FindBy(xpath = "//h3[@id='authorisation_statuses']")
    private WebElement paymentsStatusesLabel;

    @FindBy(xpath = "//h3[@id='authorisation_statuses']/following-sibling::p")
    private WebElement paymentsStatusesDescription;

    @FindBy(xpath = "//h3[@id='authorisation_statuses']/following-sibling::table")
    private WebElement paymentStatusesTable;

    @Override
    public List<WebElement> get() {
        return List.of(paymentsLabel,
                cardAccountsSelectorButton,
                paymentsStatusesLabel,
                paymentsStatusesDescription,
                paymentStatusesTable,
                header.getImageLogo(),
                header.getApplicationNameLabel(),
                header.getGetStartedLink(),
                header.getTppRegistrationLink(),
                header.getAisLink(),
                header.getPisLink(),
                header.getPiisLink(),
                header.getSigningBasketsLink(),
                header.getStatisticsLink()
        );
    }
}