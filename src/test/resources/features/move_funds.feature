Feature: Move funds between accounts

  Scenario: Person A sends 1 dollar to Person B and remains with 1 dollar
    Given an account with id "123" with balance of 3.0
    And an account with id "987" with balance of 200.0
    When account "123" sends 1.0 to account "987"
    Then the transaction is made
    And account "123" balance is 2.0
    And account "987" balance is 201.0

  Scenario: Person A sends 2 dollar to Person B and zeros banking account
    Given an account with id "123"
    And an account with id "987"
    When account "123" sends 2.0 to account "987"
    Then the transaction is made
    And account "123" balance is 0.0
    And account "987" balance is 203.0

  Scenario: Person A sends 1 dollar to Person Bbut does not have any money
    Given an account with id "123"
    And an account with id "987"
    When account "123" sends 1.0 to account "987"
    Then the transaction is not made

  Scenario: Person B gives all his money to Person B
    Given an account with id "123"
    And an account with id "987"
    When account "987" sends 203.0 to account "123"
    Then the transaction is made
    And account "123" balance is 203.0
    And account "987" balance is 0.0

  Scenario: Person A tries to send money to himself
    Given an account with id "123" with balance of 100.0
    When account "123" sends 100.0 to account "123"
    Then the transaction is not made
