package com.saltedge.config;

import com.saltedge.pom.blocks.Header;
import com.saltedge.pom.pages.AccountsPage;
import com.saltedge.pom.pages.PaymentsPage;
import com.saltedge.requests.RestTemplateRequests;
import com.saltedge.helpers.ScenarioContext;
import io.cucumber.spring.CucumberContextConfiguration;
import org.example.SimulateAis.SimulateAisApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties
@CucumberContextConfiguration
@SpringBootTest(classes = SimulateAisApplication.class)
@ComponentScan({"org.example.SimulateAis", "com.saltedge.steps", "com.saltedge.config"})
@Import({RestTemplateConfiguration.class, RestTemplateRequests.class, ScenarioContext.class, AccountsPage.class, Header.class,
PaymentsPage.class, WebDriverConfig.class})
public class CucumberSpringConfiguration {
}