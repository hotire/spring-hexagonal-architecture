package com.github.hotire.spring.hexagonal.architecture.ex.account.domain

/**
 * output
 */
interface LoadAccountPort {
    fun loadAccount(accountId: AccountId): Account
}

interface UpdateAccountPort {
    fun updateAccount(account: Account)
}

/**
 * input
 */
interface SendMoneyUseCase {
    fun sendMoney(command: SendMoneyCommand): Boolean

    data class SendMoneyCommand(
        val sourceAccountId: AccountId,
        val targetAccountId: AccountId,
        val money: Money
    )
}
