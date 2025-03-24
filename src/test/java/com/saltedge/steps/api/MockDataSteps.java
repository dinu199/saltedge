package com.saltedge.steps.api;

import com.saltedge.requests.RestTemplateRequests;
import com.saltedge.helpers.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class MockDataSteps {

    @Autowired
    private RestTemplateRequests restTemplateRequests;

    @Autowired
    private ScenarioContext scenarioContext;

    @Order(1)
    @Given("user sends a request to create mock data")
    public void iSendAPOSTRequestToCreateMockData() {
        ResponseEntity<?> insertTestData = restTemplateRequests.insertTestData();
        scenarioContext.set("mockDataResponse", insertTestData);
    }

    @Order(2)
    @Then("the response status is {} and empty response body")
    public void theResponseStatusShouldBeAndEmptyResponseBody(int expectedStatus) {
        ResponseEntity<?> mockDataResponse = scenarioContext.get("mockDataResponse", ResponseEntity.class);

       assertEquals(expectedStatus, mockDataResponse.getStatusCode().value());
        assertNotNull(mockDataResponse);
        assertNull(mockDataResponse.getBody());
        assertNotNull(mockDataResponse.getHeaders(), "Response headers should not be null");
    }
}
