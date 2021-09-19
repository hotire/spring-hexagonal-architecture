package com.github.hotire.spring.hexagonal.architecture.ex.account.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SendMoneyUseCaseService(
    private val loadAccountPort: LoadAccountPort,
    private val updateAccountPort: UpdateAccountPort
) : SendMoneyUseCase {

    @Transactional
    override fun sendMoney(command: SendMoneyUseCase.SendMoneyCommand): Boolean {
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
