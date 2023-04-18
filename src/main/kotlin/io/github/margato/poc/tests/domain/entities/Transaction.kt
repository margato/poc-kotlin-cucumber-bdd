package io.github.margato.poc.tests.domain.entities

import com.github.michaelbull.result.*
import io.github.margato.poc.tests.domain.exceptions.InvalidAccountOperationException
import io.github.margato.poc.tests.domain.validation.base.Error
import io.github.margato.poc.tests.domain.validation.errors.InactiveAccountError
import io.github.margato.poc.tests.domain.validation.errors.InsufficientFundError
import io.github.margato.poc.tests.domain.validation.errors.ReceiverIsSenderError
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*

class Transaction(
    private val from: Account,
    private val to: Account,
    private val value: BigDecimal,
) {
    private val id: String = UUID.randomUUID().toString()
    private val date: ZonedDateTime = ZonedDateTime.now()

    init {
        checkReceiverIsSender()
            .and(checkAccountsAreActive())
            .and(checkBalance())
            .onFailure {
                throw InvalidAccountOperationException("${it.code} - ${it.message}")
            }

        from.withdraw(value)
        to.deposit(value)
    }

    private fun checkAccountsAreActive(): Result<Transaction, Error> {
        if (!from.active)
            return Err(InactiveAccountError(from.id))
        if (!to.active)
            return Err(InactiveAccountError(to.id))

        return Ok(this)
    }

    private fun checkBalance(): Result<Transaction, Error> {
        if (from.getBalance() < value)
            return Err(InsufficientFundError(from.id, value = this.value, currentBalance = this.from.getBalance()))

        return Ok(this)
    }

    private fun checkReceiverIsSender(): Result<Transaction, Error> {
        if (from.id == to.id)
            return Err(ReceiverIsSenderError(from.id))

        return Ok(this)
    }

    override fun toString(): String {
        return "Transaction(from=$from, to=$to, value=$value, id='$id', date=$date)"
    }
}

