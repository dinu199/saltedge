package com.saltedge.pom.blocks;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Getter
@Component
@Slf4j
public class Header {

    private final WebDriver driver;

    @FindBy(css = "img[alt='Demo Bank BG EU']")
    private WebElement imageLogo;

    @FindBy(css = "p[class='provider-name']")
    private WebElement applicationNameLabel;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'Get Started')]")
    private WebElement getStartedLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'TPP Registration')]")
    private WebElement tppRegistrationLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'AIS')]")
    private WebElement aisLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'PIS')]")
    private WebElement pisLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'PIIS')]")
    private WebElement piisLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'Signing Baskets')]")
    private WebElement signingBasketsLink;

    @FindBy(xpath = "//a[@class='menu-link' and contains(text(),'Statistics')]")
    private WebElement statisticsLink;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}