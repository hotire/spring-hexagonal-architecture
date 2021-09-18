package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.adapter

import com.github.hotire.spring.hexagonal.architecture.ex.account.CustomPropertyChangeListener
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountEntity
import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.repository.AccountRepository
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.AccountId
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.LoadAccountPort
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.UpdateAccountPort
import org.springframework.stereotype.Service
import java.beans.PropertyChangeSupport

@Service
class AccountPersistenceAdapter(private val accountRepository: AccountRepository) :
    LoadAccountPort,
    UpdateAccountPort {

    override fun loadAccount(accountId: AccountId): Account {
        val accountEntity = accountRepository.findById(accountId.value).orElseThrow()
        return Account(AccountId(accountEntity.id), Money(accountEntity.money))
    }

    override fun updateAccount(account: Account) {
        val origin = loadAccount(accountId = account.id)
        val propertyChangeSupport = PropertyChangeSupport(origin)

        customPropertyChangeListeners().forEach {
            propertyChangeSupport.addPropertyChangeListener(
                it.propertyName,
                it
            )
        }
        customPropertyChangeListeners()
            .distinctBy { it.propertyName }
            .forEach {
                propertyChangeSupport.firePropertyChange(
                    it.propertyName,
                    it.propertyProvider(origin),
                    it.propertyProvider(account)
                )
            }

        accountRepository.save(AccountEntity(account.id.value, account.balance.value))
    }

    fun customPropertyChangeListeners(): List<CustomPropertyChangeListener<Account, *>> = listOf(object :
            CustomPropertyChangeListener<Account, Money> {
            override val propertyName: String
                get() = "balance"
            override val propertyProvider: Account.() -> Money
                get() = { this.balance }

            override fun propertyChange(oldValue: Money?, newValue: Money?) {
                println(oldValue)
                println(newValue)
            }
        })
}
