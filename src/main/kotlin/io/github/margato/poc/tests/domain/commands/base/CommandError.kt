package io.github.margato.poc.tests.domain.commands.base


abstract class CommandError(
    val code: String,
    val message: String
)