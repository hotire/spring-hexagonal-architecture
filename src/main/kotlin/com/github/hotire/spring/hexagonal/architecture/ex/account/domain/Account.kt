package com.github.hotire.spring.hexagonal.architecture.ex.account.domain

@JvmInline
value class AccountId(val value: Long)
@JvmInline
value class Money(val value: Int)

data class Account(
    var id: AccountId,
    var balance: Money
)
