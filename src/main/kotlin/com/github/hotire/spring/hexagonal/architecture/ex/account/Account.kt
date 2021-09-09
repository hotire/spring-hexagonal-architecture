package com.github.hotire.spring.hexagonal.architecture.ex.account

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@JvmInline
value class AccountId(val value: Long)
@JvmInline
value class Money(val value: Int)

data class Account(
    var id: AccountId,
    var balance: Money
)

interface SendMoney {
    fun sendMoney(command: SendMoneyCommand): Boolean

    data class SendMoneyCommand(
        val sourceAccountId: AccountId,
        val targetAccountId: AccountId,
        val money: Money
    )
}

@Service
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val updateAccountPort: UpdateAccountPort
) : SendMoney {

    @Transactional
    override fun sendMoney(command: SendMoney.SendMoneyCommand): Boolean {
        val sourceAccount = loadAccountPort.loadAccount(command.sourceAccountId)
        val targetAccount = loadAccountPort.loadAccount(command.targetAccountId)

        if (sourceAccount.balance.value < command.money.value) {
            return false
        }

        sourceAccount.balance = Money(sourceAccount.balance.value - command.money.value)
        targetAccount.balance = Money(targetAccount.balance.value + command.money.value)

        updateAccountPort.updateAccount(sourceAccount)
        updateAccountPort.updateAccount(targetAccount)

        return true
    }
}

interface LoadAccountPort {
    fun loadAccount(accountId: AccountId): Account
}

interface UpdateAccountPort {
    fun updateAccount(account: Account)
}

@RequestMapping("/money")
@RestController
class SendMoneyController(val sendMoney: SendMoney) {

    @GetMapping("/send")
    fun sendMoney(
        @RequestParam("sourceAccountId") sourceAccountId: Long,
        @RequestParam("targetAccountId") targetAccountId: Long,
        @RequestParam("amount") amount: Int
    ) {
        sendMoney.sendMoney(
            SendMoney.SendMoneyCommand(
                AccountId(sourceAccountId),
                AccountId(targetAccountId),
                Money(amount)
            )
        )
    }
}
