Feature: Validate API Documentation UI

  @UI
  Scenario Outline: Verify API Documentation for Account Information
    Given user navigates to the <page> page
    Then user verifies all expected elements are present on the <page>
    Examples:
      | page     |
      | Accounts |

  @UI
  Scenario Outline: Verify API Documentation for Payment Information
    Given user navigates to the <page> page
    Then user verifies all expected elements are present on the <page>
    Examples:
      | page     |
      | Payments |