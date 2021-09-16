package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.repository

import com.github.hotire.spring.hexagonal.architecture.ex.account.persistence.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long>
