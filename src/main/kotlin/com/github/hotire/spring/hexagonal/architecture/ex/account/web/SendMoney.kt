package com.github.hotire.spring.hexagonal.architecture.ex.account.web

import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money

interface SendMoney {
    fun sendMoney(command: SendMoneyCommand): Boolean

    data class SendMoneyCommand(
        val sourceAccountId: AccountId,
        val targetAccountId: AccountId,
        val money: Money
    )
}
