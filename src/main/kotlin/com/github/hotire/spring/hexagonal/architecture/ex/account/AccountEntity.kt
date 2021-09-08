package com.github.hotire.spring.hexagonal.architecture.ex.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class AccountEntity(
    @Id
    var id: Long,
    var money: Int
)

interface AccountRepository : JpaRepository<AccountEntity, Long>

@Service
class AccountPersistenceService(private val accountRepository: AccountRepository) {

    fun findById(accountId: AccountId) : Account {
        val accountEntity = accountRepository.findById(accountId.value).orElseThrow()
        return Account(AccountId(accountEntity.id), Money(accountEntity.money))
    }
}
