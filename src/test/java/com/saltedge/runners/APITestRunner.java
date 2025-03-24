package com.saltedge.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saltedge.steps", "com.saltedge.config"},
        plugin = {"pretty", "html:target/cucumber-api-report.html"},
        tags = "@API"
)
public class APITestRunner {
    static {
        System.setProperty("spring.profiles.active", "api");
    }
}