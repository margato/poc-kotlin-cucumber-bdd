package io.github.margato.poc.tests.application.entities

import java.math.BigDecimal

data class Account(
    val id: String,
    val branchId: String,
    val balance: BigDecimal,
    var active: Boolean
)