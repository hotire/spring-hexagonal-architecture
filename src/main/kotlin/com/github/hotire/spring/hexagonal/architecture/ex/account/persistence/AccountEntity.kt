package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class AccountEntity(
    @Id
    var id: Long,
    var balance: Int
)
