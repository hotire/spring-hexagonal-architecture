package com.github.hotire.spring.hexagonal.architecture.ex.account.service

import com.github.hotire.spring.hexagonal.architecture.ex.account.CustomPropertyChangeListener
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Account
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.domain.Money
import com.github.hotire.spring.hexagonal.architecture.ex.account.service.port.LoadAccountPort
import java.beans.PropertyChangeSupport

class UpdateAccountService(private val loadAccountPort: LoadAccountPort) {

    fun update(account: Account) {
        val origin = loadAccountPort.loadAccount(accountId = account.id)
        val propertyChangeSupport = PropertyChangeSupport(origin)

        customPropertyChangeListeners().forEach { propertyChangeSupport.addPropertyChangeListener(it.propertyName, it) }
        customPropertyChangeListeners()
            .distinctBy { it.propertyName }
            .forEach {
                propertyChangeSupport.firePropertyChange(it.propertyName, it.propertyProvider(origin), it.propertyProvider(account))
            }
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
