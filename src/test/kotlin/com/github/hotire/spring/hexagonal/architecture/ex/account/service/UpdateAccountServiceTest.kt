package com.github.hotire.spring.hexagonal.architecture.ex.account.service

import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.LoadAccountPort
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.UpdateAccountService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

internal class UpdateAccountServiceTest {

    @Test
    fun update() {
        // given
        val loadAccountPort = Mockito.mock(LoadAccountPort::class.java)
        val updateAccountService = UpdateAccountService(loadAccountPort)
        val account = Account(AccountId(1), Money(1))
        val origin = Account(account.id, Money(2))

        given(loadAccountPort.loadAccount(account.id)).willReturn(origin)

        // when
        updateAccountService.update(account)

        // then
    }
}
