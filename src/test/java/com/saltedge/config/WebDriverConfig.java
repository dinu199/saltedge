package com.saltedge.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class WebDriverConfig {

    @Bean
    @Scope("prototype")
    public WebDriver webDriver() {
        log.info("Exposing WebDriver bean");
        return DriverManager.getDriver();
    }
}
