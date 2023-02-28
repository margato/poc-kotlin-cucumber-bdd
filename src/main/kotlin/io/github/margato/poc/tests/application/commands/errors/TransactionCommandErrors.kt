package io.github.margato.poc.tests.application.commands.errors

import io.github.margato.poc.tests.application.commands.base.CommandError

data class InsufficientFundCommandError(val accountId: String) :
    CommandError("400-001", "Account $accountId does not have enough funds")

data class InactiveAccountCommandError(val accountId: String) :
    CommandError("400-002", "Account $accountId is not active")