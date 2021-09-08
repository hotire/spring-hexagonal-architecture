package com.github.hotire.spring.hexagonal.architecture.ex.account

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@JvmInline
value class AccountId(val value: Long)
@JvmInline
value class Money(val value: Int)

class Account(
    val id: AccountId,
    val balance: Money
)

interface SendMoney {
    fun sendMoney(command: SendMoneyCommand): Boolean

    class SendMoneyCommand(
        val sourceAccountId: AccountId,
        val targetAccountId: AccountId,
        val money: Money
    )
}

@Service
class SendMoneyService(private val accountPersistenceService: AccountPersistenceService) : SendMoney {
    override fun sendMoney(command: SendMoney.SendMoneyCommand): Boolean {
        val account = accountPersistenceService.findById(command.sourceAccountId)
        return true
    }
}

@RequestMapping("/money")
@RestController
class SendMoneyController(val sendMoney: SendMoney) {

    @GetMapping("/send")
    fun sendMoney(
        @RequestParam("sourceAccountId") sourceAccountId: Long,
        @RequestParam("targetAccountId") targetAccountId: Long,
        @RequestParam("amount") amount: Int) {
        sendMoney.sendMoney(SendMoney.SendMoneyCommand(AccountId(sourceAccountId), AccountId(targetAccountId), Money(amount)))
    }
}
