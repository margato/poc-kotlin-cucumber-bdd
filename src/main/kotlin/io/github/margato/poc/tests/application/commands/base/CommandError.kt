package io.github.margato.poc.tests.application.commands.base


abstract class CommandError(
    val code: String,
    val message: String
)