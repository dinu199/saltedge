package com.saltedge.runners;

import com.saltedge.config.DriverManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saltedge.steps", "com.saltedge.config", "com.saltedge.hooks"},
        plugin = {"pretty"},
        tags = "@UI"
)
@ActiveProfiles("ui")
@ContextConfiguration(classes = {DriverManager.class})
public class UITestRunner {
    static {
        System.setProperty("spring.profiles.active", "ui"); // Activate UI profile
    }
}
