Feature: Create Mock Data

  @API
  Scenario: Successfully create mock data
    Given user sends a request to create mock data
    Then the response status is 200 and empty response body