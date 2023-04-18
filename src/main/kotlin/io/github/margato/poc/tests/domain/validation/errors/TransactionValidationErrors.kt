package io.github.margato.poc.tests.domain.validation.errors

import io.github.margato.poc.tests.domain.validation.base.Error
import java.math.BigDecimal

data class InsufficientFundError(val accountId: String, val value: BigDecimal, val currentBalance: BigDecimal) :
    Error(
        "400-001",
        "Account $accountId does not have enough funds. Trying to send $value but current balance is: $currentBalance"
    )

data class InactiveAccountError(val accountId: String) :
    Error("400-002", "Account $accountId is not active")

data class ReceiverIsSenderError(val accountId: String) :
    Error("400-003", "Account $accountId cannot send money to itself")