Feature: Accounts and Transactions Feature

  @API
  Scenario: Successfully retrieve card accounts data
    Given user sends a request to create mock data
    And user sends a request to create consent
      | consent id |
      | 0000002    |
    When user sends a request to change consent status
    And user sends a request to retrieve card account information
    Then the response status is 200 and the account body

  @API
  Scenario: Successfully retrieve account transactions data
    Given user sends a request to create mock data
    And user sends a request to create consent
      | consent id |
      | 0000002    |
    When user sends a request to change consent status
    And user sends a request to retrieve account transactions information
    Then the response status is 200 and the account transactions body