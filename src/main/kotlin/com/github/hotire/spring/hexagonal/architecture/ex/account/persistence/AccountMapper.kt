package com.github.hotire.spring.hexagonal.architecture.ex.account.persistence

import com.github.hotire.spring.hexagonal.architecture.ex.account.domain.Account
import org.mapstruct.Mapper

@Mapper
interface AccountMapper {
    fun toEntity(domain: Account): AccountEntity
    fun toDomain(entity: AccountEntity): Account
}
