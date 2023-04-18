package io.github.margato.poc.tests.cucumber

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.github.margato.poc.tests.domain.entities.Account
import io.github.margato.poc.tests.domain.entities.Transaction
import io.github.margato.poc.tests.state.AccountTestRepository
import org.junit.jupiter.api.Assertions.*

class TransactionStepsDefinition {
    private val scenarioState = mutableMapOf<String, Any>()

    @Before
    fun before() {
        scenarioState.remove("transaction-exception")
        scenarioState.remove("transaction")
    }

    @Given("an account with id {string} with balance of {double}")
    fun an_account_with_id(id: String, balance: Double) {
        val account = Account(
            id = id,
            branchId = "1500",
            balance = balance.toBigDecimal()
        )

        AccountTestRepository.add(account)
    }

    @Given("an account with id {string}")
    fun an_account_with_id(id: String) {
        if (!AccountTestRepository.contains(id))
            AccountTestRepository.add(
                Account(
                    id = id,
                    branchId = "1500"
                )
            )
    }

    @When("account {string} sends {double} to account {string}")
    fun account_sends_to_account(sender: String, value: Double, receiver: String) =
        runCatching {
            Transaction(
                from = AccountTestRepository.get(sender)!!,
                to = AccountTestRepository.get(receiver)!!,
                value = value.toBigDecimal(),
            )
        }.onFailure { ex ->
            scenarioState["transaction-exception"] = ex
        }.onSuccess { transaction ->
            scenarioState["transaction"] = transaction
        }

    @Then("the transaction is made")
    fun the_transaction_is_made() {
        assertTrue(scenarioState.containsKey("transaction"))
    }

    @Then("the transaction is not made")
    fun the_transaction_is_not_made() {
        assertFalse(scenarioState.containsKey("transaction"))
    }

    @Then("account {string} balance is {double}")
    fun account_balance_is(id: String, balance: Double) {
        assertEquals(balance.toBigDecimal(), AccountTestRepository.get(id)!!.getBalance())
    }
}