package com.saltedge.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class RestTemplateRequests {

    @Autowired
    private RestTemplate restTemplate;

    @Value("#{'${api.base.url}'}")
    private String baseUrl;

    @Value("#{'${api.createMockData}'}")
    private String createMockDataEndpoint;

    @Value("#{'${api.createConsent}'}")
    private String createConsent;

    @Value("#{'${api.getConsentStatus}'}")
    private String consentStatus;

    @Value("#{'${api.showConsent}'}")
    private String showConsent;

    @Value("#{'${api.deleteConsent}'}")
    private String deleteConsent;

    @Value("#{'${api.changeConsentStatus}'}")
    private String changeStatus;

    @Value("#{'${api.getAccountIndex}'}")
    private String cardAccounts;

    @Value("#{'${api.getAccountsTransactions}'}")
    private String cardAccountsTransactions;

    private ResponseEntity<String> response;
    private HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<String> insertTestData() {
        String insertTestDataEndpoint = baseUrl + createMockDataEndpoint;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        String body = "";

        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/payload/insert_mock_data.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            response = restTemplate.exchange(insertTestDataEndpoint, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> createConsent(String consentId) {
        String createConsentEndpoint = baseUrl + createConsent + consentId;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        String body = "";

        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/payload/insert_mock_data.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        try {
            response = restTemplate.exchange(createConsentEndpoint, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> getConsentStatus(String consentId) {
        consentStatus = consentStatus.replace("{consentId}", consentId);
        String getConsentEndpoint = baseUrl + consentStatus;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getConsentEndpoint, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> getConsent(String consentId) {
        showConsent = showConsent.replace("{consentId}", consentId);
        String getConsentEndpoint = baseUrl + showConsent;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getConsentEndpoint, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> deleteConsent(String consentId) {
        deleteConsent = deleteConsent.replace("{consentId}", consentId);
        String getConsentEndpoint = baseUrl + deleteConsent;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getConsentEndpoint, HttpMethod.DELETE, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> changeConsentStatus(String consentId) {
        changeStatus = changeStatus.replace("{consentId}", consentId);
        String getConsentEndpoint = baseUrl + changeStatus;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getConsentEndpoint, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> getCardAccountInfo(String consentId) {
        String getAccountEndpoint = baseUrl + cardAccounts;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        headers.add("Consent-Id", consentId);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getAccountEndpoint, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }

    public ResponseEntity<String> gerAccountTransactionsInfo(String consentId) {
        String getAccountTransactionsEndpoint = baseUrl + cardAccountsTransactions;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-Type", "application/json");
        headers.set("Consent-Id", consentId);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(getAccountTransactionsEndpoint, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        return response;
    }
}