package com.github.hotire.spring.hexagonal.architecture.ex.account.service.port

import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId

interface LoadAccountPort {
    fun loadAccount(accountId: AccountId): Account
}

interface UpdateAccountPort {
    fun updateAccount(account: Account)
}