package io.github.margato.poc.tests.cucumber

import io.github.margato.poc.tests.domain.entities.Account

object AccountTestRepository {
    private val accounts = mutableMapOf<String, Account>()

    fun add(account: Account) = accounts.put(account.id, account)

    fun remove(id: String) = accounts.remove(id)

    fun clear() = accounts.clear()

    fun get(id: String) = accounts[id]

    fun contains(id: String) = accounts.containsKey(id)
}