package com.saltedge.pom.pages;

import com.saltedge.pom.blocks.Header;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
@Slf4j
public abstract class BasePage {

    @Autowired
    private Header header;

    @Autowired(required = false)
    protected WebDriver driver;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }
}