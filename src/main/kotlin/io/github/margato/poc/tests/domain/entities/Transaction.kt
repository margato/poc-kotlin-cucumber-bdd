package io.github.margato.poc.tests.domain.entities

import com.github.michaelbull.result.*
import io.github.margato.poc.tests.domain.commands.base.CommandError
import io.github.margato.poc.tests.domain.commands.errors.InactiveAccountCommandError
import io.github.margato.poc.tests.domain.commands.errors.InsufficientFundCommandError
import io.github.margato.poc.tests.domain.exceptions.InvalidAccountOperationException
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*

class Transaction(
    private val from: Account,
    private val to: Account,
    private val value: BigDecimal,
) {
    val id: String = UUID.randomUUID().toString()
    val date: ZonedDateTime = ZonedDateTime.now()

    init {
        checkAccountsAreActive()
            .and(checkBalance())
            .onFailure {
                throw InvalidAccountOperationException("${it.code} - ${it.message}")
            }
    }

    private fun checkAccountsAreActive(): Result<Transaction, CommandError> {
        if (!from.active)
            return Err(InactiveAccountCommandError(from.id))
        if (!to.active)
            return Err(InactiveAccountCommandError(to.id))

        return Ok(this)
    }

    private fun checkBalance(): Result<Transaction, CommandError> {
        if (from.balance.compareTo(value) == -1)
            return Err(InsufficientFundCommandError(from.id))

        return Ok(this)
    }
}

