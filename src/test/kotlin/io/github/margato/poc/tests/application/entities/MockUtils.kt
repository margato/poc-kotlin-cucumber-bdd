package io.github.margato.poc.tests.application.entities

import java.math.BigDecimal
import java.util.*

object MockUtils {

    fun mockAccount(id: String?, active: Boolean, balance: Double): Account {
        return Account(
            id = id ?: UUID.randomUUID().toString(),
            branchId = UUID.randomUUID().toString(),
            BigDecimal(balance),
            active
        )
    }

}