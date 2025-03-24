package com.saltedge.pom.pages;

import com.saltedge.pom.blocks.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountsPage extends BasePage implements Page {

    @Autowired
    private Header header;

    @FindBy(xpath = "//h1[@id='accounts']")
    private WebElement accountsLabel;

    @FindBy(css = "[href='#card-accounts'][class='prev-link']")
    private WebElement cardAccountsSelectorButton;

    @FindBy(css = "[href='#refresh-data'][class='next-link']")
    private WebElement refreshDataSelectorButton;

    @FindBy(xpath = "//h3[@id='account_statuses']")
    private WebElement accountStatusesLabel;

    @FindBy(xpath = "//h3[@id='account_statuses']/following-sibling::p")
    private WebElement accountStatusesDescription;

    @FindBy(xpath = "//h3[@id='account_statuses']/following-sibling::table")
    private WebElement accountsStatusesTable;

    @FindBy(xpath = "//following-sibling::table")
    private List<WebElement> tables;

    @FindBy(xpath = "//h2[@id='accounts-transactions']")
    private WebElement accountsTransactionsLabel;

    @FindBy(xpath = "//h2[@id='accounts-transactions']/following-sibling::p")
    private WebElement accountsTransactionsDescription;

    @Override
    public List<WebElement> get() {
        return List.of(
                accountsLabel,
                cardAccountsSelectorButton,
                refreshDataSelectorButton,
                accountStatusesLabel,
                accountStatusesDescription,
                accountsStatusesTable,
                accountsTransactionsLabel,
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
