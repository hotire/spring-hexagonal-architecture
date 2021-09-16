package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.adapter

import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountEntity
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.repository.AccountRepository
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.LoadAccountPort
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.UpdateAccountPort
import org.springframework.stereotype.Service

@Service
class AccountPersistenceAdapter(private val accountRepository: AccountRepository) :
    LoadAccountPort,
    UpdateAccountPort {

    override fun loadAccount(accountId: AccountId): Account {
        val accountEntity = accountRepository.findById(accountId.value).orElseThrow()
        return Account(AccountId(accountEntity.id), Money(accountEntity.money))
    }

    override fun updateAccount(account: Account) {
        accountRepository.save(AccountEntity(account.id.value, account.balance.value))
    }
}
