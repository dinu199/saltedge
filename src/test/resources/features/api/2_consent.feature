Feature: Consent feature

  @API
  Scenario: Successfully create consent
    Given user sends a request to create consent
      | consent id |
      | 0000000    |
    Then the response status is 201 and the consent body

  @API
  Scenario: Successfully get consent status
    Given user sends a request to create consent
      | consent id |
      | 0000001    |
    When user sends a request to get the consent status
    Then the response status is 200 and the consent status

  @API
  Scenario: Successfully get consent
    Given user sends a request to create consent
      | consent id |
      | 0000000    |
    When user sends a request to get the consent
    Then the response status is 200 and the consent body

  @API
  Scenario: Successfully delete consent
    Given user sends a request to create consent
      | consent id |
      | 0000000    |
    When user sends a request to delete consent
    Then the response status should be 204

  @API
  Scenario: Change consent status
    Given user sends a request to create consent
      | consent id |
      | 0000002    |
    When user sends a request to change consent status
    Then the response status should be 200