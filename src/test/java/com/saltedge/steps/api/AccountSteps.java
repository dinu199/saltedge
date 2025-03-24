package com.saltedge.steps.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltedge.requests.RestTemplateRequests;
import com.saltedge.helpers.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class AccountSteps {

    @Autowired
    private RestTemplateRequests restTemplateRequests;

    @Autowired
    private ScenarioContext scenarioContext;

    ObjectMapper objectMapper = new ObjectMapper();

    @And("user sends a request to retrieve card account information")
    public void userSendsARequestToRetrieveCardAccountInformation() {
        String id = scenarioContext.getValue("consentId");
        ResponseEntity<String> getCardAccount = restTemplateRequests.getCardAccountInfo(id);
        scenarioContext.set("cardAccount", getCardAccount);
    }

    @Given("the response status is {} and the account body")
    public void userSendsARequestToGetAccountInfo(int expectedStatus) throws JsonProcessingException {
        ResponseEntity<String> getCardAccount = scenarioContext.get("cardAccount", ResponseEntity.class);
        String consentId = scenarioContext.getValue("consentId");
        JsonNode jsonNode = objectMapper.readTree(getCardAccount.getBody());
        String accountId = jsonNode.get("accountId").asText();
        String maskedPan = jsonNode.get("maskedPan").asText();
        String ch = String.valueOf(consentId.charAt(consentId.length() - 1));

        assertEquals(expectedStatus, getCardAccount.getStatusCode().value());
        assertNotNull(getCardAccount);
        assertNotNull(getCardAccount.getBody());
        assertNotNull(getCardAccount.getHeaders());
        assertTrue(getCardAccount.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE), "Missing Content-Type header");
        assertEquals(consentId, accountId.replace("ACC", ""));
        assertTrue(maskedPan.endsWith("324" + ch));
    }

    @And("user sends a request to retrieve account transactions information")
    public void userSendsARequestToRetrieveAccountTransactionsInformation() {
        String id = scenarioContext.getValue("consentId");
        ResponseEntity<String> getCardAccount = restTemplateRequests.gerAccountTransactionsInfo(id);
        scenarioContext.set("cardAccountTransactions", getCardAccount);
    }

    @Then("the response status is {} and the account transactions body")
    public void theResponseStatusIsAndTheAccountTransactionsBody(int expectedStatus) throws JsonProcessingException {
        ResponseEntity<String> getCardAccount = scenarioContext.get("cardAccountTransactions", ResponseEntity.class);
        String consentId = scenarioContext.getValue("consentId");
        JsonNode balances = objectMapper.readTree(getCardAccount.getBody()).get("balances");
        JsonNode cardTransactions = objectMapper.readTree(getCardAccount.getBody()).get("cardTransactions");
        JsonNode book = objectMapper.readTree(getCardAccount.getBody()).get("booked");
        JsonNode cardAccount = objectMapper.readTree(getCardAccount.getBody()).get("cardAccount");
        JsonNode balanceAmountNode = balances.get(0).get("balanceAmount").get("amount");
        String currency = balances.get(0).get("balanceAmount").get("currency").asText();
        String accountId = cardAccount.get("accountId").asText();
        String maskedPan = cardAccount.get("maskedPan").asText();

        String ch = String.valueOf(consentId.charAt(consentId.length() - 1));
        int consentNumericValue = Integer.parseInt(consentId);
        int expectedBalance = 100 + (consentNumericValue * 50);
        int actualBalance = balanceAmountNode.asInt();


        assertEquals(expectedBalance, actualBalance,
                "Balance amount does not match expected value for Consent-Id: " + consentId);
        assertEquals("EUR", currency.replace("\"", ""),
                "Currency does not match expected value: " + consentId);
        assertNotNull(balances, "Balances node is missing!");
        assertNotNull(getCardAccount);
        assertNotNull(getCardAccount.getBody());
        assertNotNull(getCardAccount.getHeaders());
        assertTrue(getCardAccount.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE), "Missing Content-Type header");
        assertEquals(consentId, accountId.replace("ACC", ""));
        assertTrue(maskedPan.endsWith("324" + ch));
    }
}
