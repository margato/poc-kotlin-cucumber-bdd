package io.github.margato.poc.tests.cucumber

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.github.margato.poc.tests.domain.entities.Account
import io.github.margato.poc.tests.domain.entities.MockUtils.mockAccount
import io.github.margato.poc.tests.domain.entities.Transaction
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import java.math.BigDecimal

class TransactionStepsDefinition {
    private var accounts: MutableMap<String, Account> = mutableMapOf()
    private var transaction: Transaction? = null

    @Before
    fun beforeEachScenario() {
        accounts.clear()
        transaction = null
    }

    @Given("an account with id {string} and balance of {double}")
    fun an_account_with_id_and_balance_of(id: String, amount: Double) {
        accounts[id] = mockAccount(id, true, amount)
    }

    @Given("account {string} is {string}")
    fun account_tries_to_send_to_account(id: String, status: String) {
        when (status) {
            "active" -> { accounts[id]?.active = true }
            "inactive" -> { accounts[id]?.active = false }
            else -> { accounts[id]?.active = false }
        }
    }

    @When("account {string} tries to send {double} to account {string}")
    fun account_tries_to_send_to_account(fromAccountId: String, amount: Double, toAccountId: String) {
        runCatching {
            transaction = Transaction(
                accounts[fromAccountId]!!,
                accounts[toAccountId]!!,
                BigDecimal(amount)
            )
        }.onFailure {
            transaction = null
        }
    }

    @Then("the transaction can be made")
    fun the_transaction_can_be_made() {
        assertNotNull(transaction)
    }

    @Then("the transaction can not be made")
    fun the_transaction_can_not_be_made() {
        assertNull(transaction)
    }

}