package com.github.hotire.spring.hexagonal.architecture.ex.account

import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.Account2
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountMapper
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountMeta
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

internal class AccountMapperTest {

    @Test
    fun toEntity() {
        // given
        val account = Account2(id = AccountId(1L), balance = 10, meta = AccountMeta("hello"))

        // when
        val result = Mappers.getMapper(AccountMapper::class.java).toEntity(account)

        // then
        println(result.id)
        println(result.balance)
        println(result.meta)
    }
}
