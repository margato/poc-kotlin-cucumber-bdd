package io.github.margato.poc.tests.domain.entities

import java.math.BigDecimal
import java.util.*

data class Account(
    val id: String = UUID.randomUUID().toString(),
    val branchId: String,
    private var balance: BigDecimal = BigDecimal.ZERO,
    var active: Boolean = true
) {
    fun withdraw(value: BigDecimal) {
        if (value <= balance)
            balance = balance.minus(value)
    }

    fun deposit(value: BigDecimal) {
        if (value > BigDecimal.ZERO)
            balance = balance.plus(value)
    }

    fun getBalance() = balance
}