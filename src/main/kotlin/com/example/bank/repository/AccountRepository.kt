package com.example.bank.repository

import com.example.bank.entity.Account
import com.example.bank.entity.Client
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository : CrudRepository<Account, UUID> {
    fun findByClient(client: Client): List<Account>
}