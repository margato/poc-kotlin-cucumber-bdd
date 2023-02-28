package io.github.margato.poc.tests.application.commands

import io.github.margato.poc.tests.application.commands.base.Command
import java.math.BigDecimal

data class MoveFundsCommand(
    val fromAccountId: String,
    val toAccountId: String,
    val value: BigDecimal
) : Command