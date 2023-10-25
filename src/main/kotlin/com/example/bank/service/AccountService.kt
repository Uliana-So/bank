package com.example.bank.service


import com.example.bank.dto.AccountResponse
import com.example.bank.entity.Account
import com.example.bank.entity.Client
import com.example.bank.repository.AccountRepository
import com.example.bank.repository.ClientRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.List

@Service
class AccountService(val clientRepository: ClientRepository, val accountRepository: AccountRepository) {
    fun getListByClient(id: UUID): List<UUID> {
        val client: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        val accounts: List<Account> = accountRepository.findByClient(client)
        return accounts.map { toResponseDTO(it).id }
    }

    fun getId(id: UUID): AccountResponse {
        val accountResponse: Account =
            accountRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        return toResponseDTO(accountResponse)
    }

    fun addByClient(id: UUID): AccountResponse {
        val client: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        val newAccount: Account = accountRepository.save(Account(client = client))
        return toResponseDTO(newAccount)
    }

    fun remove(id: UUID) {
        val account: Account = accountRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        accountRepository.delete(account)
    }

    private fun toResponseDTO(account: Account): AccountResponse {
        return AccountResponse(
            id = account.id,
            balance = account.balance,
            createdAt = account.createdAt.toString(),
            updatedAt = account.updatedAt.toString(),
        )
    }
}