package com.github.hotire.spring.hexagonal.architecture.ex.account.web

import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.SendMoneyUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/money")
@RestController
class SendMoneyController(val sendMoneyUseCase: SendMoneyUseCase) {

    @GetMapping("/send")
    fun sendMoney(
        @RequestParam("sourceAccountId") sourceAccountId: Long,
        @RequestParam("targetAccountId") targetAccountId: Long,
        @RequestParam("amount") amount: Int
    ) {
        sendMoneyUseCase.sendMoney(
            SendMoneyUseCase.SendMoneyCommand(
                AccountId(sourceAccountId),
                AccountId(targetAccountId),
                Money(amount)
            )
        )
    }
}
