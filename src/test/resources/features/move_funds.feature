Feature: Move funds between accounts

  Scenario: Sender has enough money
    Given an account with id "1" and balance of 10.0
    Given an account with id "2" and balance of 30.0
    When account "1" tries to send 5.0 to account "2"
    Then the transaction can be made

  Scenario: Sender does not have enough money
    Given an account with id "1" and balance of 0.0
    Given an account with id "2" and balance of 30.0
    When account "1" tries to send 5.0 to account "2"
    Then the transaction can not be made

  Scenario: Sender is trying to send money to an inactive account
    Given an account with id "1" and balance of 300000.0
    Given an account with id "2" and balance of 30.0
    Given account "2" is "inactive"
    When account "1" tries to send 5.0 to account "2"
    Then the transaction can not be made

  Scenario: Sender is trying to send money from an inactive account
    Given an account with id "1" and balance of 300000.0
    Given an account with id "2" and balance of 30.0
    Given account "1" is "inactive"
    When account "1" tries to send 5.0 to account "2"
    Then the transaction can not be made