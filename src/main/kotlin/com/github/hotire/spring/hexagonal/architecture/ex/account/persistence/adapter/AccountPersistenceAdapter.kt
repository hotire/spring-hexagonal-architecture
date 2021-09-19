package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.adapter

import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.LoadAccountPort
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.UpdateAccountPort
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountEntity
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.repository.AccountRepository
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
