package com.github.hotire.spring.hexagonal.architecture.ex.account.service.port

import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money

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
