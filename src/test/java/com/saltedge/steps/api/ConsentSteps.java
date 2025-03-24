package com.saltedge.steps.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltedge.requests.RestTemplateRequests;
import com.saltedge.helpers.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ConsentSteps {

    @Autowired
    private RestTemplateRequests restTemplateRequests;

    @Autowired
    private ScenarioContext scenarioContext;

    ObjectMapper objectMapper = new ObjectMapper();

    @Given("user sends a request to create consent")
    public void userSendsARequestToCreateConsent(DataTable dataTable) {
        List<Map<String, String>> consentData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> map : consentData) {
            String id = map.get("consent id");

            ResponseEntity<?> createConsent = restTemplateRequests.createConsent(id);
            scenarioContext.set("consent", createConsent);
            scenarioContext.setValue("consentId", id);
        }
    }

    @Then("the response status is {} and the consent body")
    public void theResponseStatusShouldBeAndTheConsentBody(int expectedStatus) throws JsonProcessingException {
        ResponseEntity<String> getConsent = scenarioContext.get("consent", ResponseEntity.class);
        String expectedConsentId = scenarioContext.getValue("consentId");
        JsonNode jsonNode = objectMapper.readTree(getConsent.getBody());
        String actualConsentId = jsonNode.get("consentId").asText();

        assertEquals(expectedStatus, getConsent.getStatusCode().value());
        assertNotNull(getConsent);
        assertNotNull(getConsent.getBody());
        assertNotNull(getConsent.getHeaders());
        assertTrue(getConsent.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE), "Missing Content-Type header");
        assertEquals("application/json", getConsent.getHeaders().getContentType().toString());
        assertEquals(expectedConsentId, actualConsentId);
    }

    @Given("user sends a request to get the consent status")
    public void userSendsARequestToGetTheConsentStatus() {
            String id = scenarioContext.getValue("consentId");
            ResponseEntity<?> getConsent = restTemplateRequests.getConsentStatus(id);
            scenarioContext.set("getConsent", getConsent);
    }

    @Then("the response status is {} and the consent status")
    public void theResponseStatusShouldBeAndTheConsentStatus(int expectedStatus) throws JsonProcessingException {
        ResponseEntity<String> getConsent = scenarioContext.get("getConsent", ResponseEntity.class);
        JsonNode jsonNode = objectMapper.readTree(getConsent.getBody());
        String actualConsentStatus = jsonNode.get("consent_status").asText();

        assertEquals(expectedStatus, getConsent.getStatusCode().value());
        assertTrue(getConsent.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE), "Missing Content-Type header");
        assertNotNull(getConsent);
        assertNotNull(getConsent.getBody());
        assertNotNull(getConsent.getHeaders());
        assertEquals("application/json", getConsent.getHeaders().getContentType().toString());
        assertTrue(actualConsentStatus.equals("invalid") || actualConsentStatus.equals("valid"),
                "Consent status should be either 'invalid' or 'valid'");
    }

    @Given("user sends a request to get the consent")
    public void userSendsARequestToGetTheConsent() {
            String id = scenarioContext.getValue("consentId");
            ResponseEntity<?> getConsent = restTemplateRequests.getConsent(id);
            scenarioContext.set("consent", getConsent);
    }

    @When("user sends a request to change consent status")
    public void userSendsARequestToChangeConsentStatus() {
        String id = scenarioContext.getValue("consentId");
        ResponseEntity<?> getConsent = restTemplateRequests.changeConsentStatus(id);
        scenarioContext.set("consent", getConsent);
    }

    @Given("user sends a request to delete consent")
    public void userSendsARequestToDeleteConsent() {
        String id = scenarioContext.getValue("consentId");
        ResponseEntity<?> deleteConsent = restTemplateRequests.deleteConsent(id);
        scenarioContext.set("consent", deleteConsent);
    }

    @Then("the response status should be {}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        ResponseEntity<String> getDeleteDataResponse = scenarioContext.get("consent", ResponseEntity.class);

        assertEquals(expectedStatus, getDeleteDataResponse.getStatusCode().value());
        assertNotNull(getDeleteDataResponse);
        assertNull(getDeleteDataResponse.getBody());
    }
}
