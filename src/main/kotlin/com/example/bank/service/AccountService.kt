package com.example.bank.service


import com.example.bank.dto.AccountResponse
import com.example.bank.dto.TransactionRequest
import com.example.bank.entity.Account
import com.example.bank.entity.Client
import com.example.bank.entity.TypeAccount
import com.example.bank.exception.BankException
import com.example.bank.exception.ErrorCode
import com.example.bank.repository.AccountRepository
import com.example.bank.repository.ClientRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.List

@Service
class AccountService(
    val clientRepository: ClientRepository,
    val accountRepository: AccountRepository
) {
    fun getListByClient(id: UUID): List<UUID> {
        val client: Client = clientRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.CLIENT_NOT_FOUND)
        val accounts: List<Account> = accountRepository.findByClient(client)
        return accounts.map { toResponseDTO(it).id }
    }

    fun getId(id: UUID): AccountResponse {
        val accountResponse: Account =
            accountRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.ACCOUNT_NOT_FOUND)
        return toResponseDTO(accountResponse)
    }

    fun addByClient(
        id: UUID,
        type: TypeAccount
    ): AccountResponse {
        val client: Client = clientRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.CLIENT_NOT_FOUND)
        val newAccount: Account = accountRepository.save(
            Account(
                client = client,
                type = type
            )
        )
        return toResponseDTO(newAccount)
    }

    fun remove(id: UUID) {
        val account: Account = accountRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.ACCOUNT_NOT_FOUND)
        accountRepository.delete(account)
    }

    fun refill(
        id: UUID,
        amount: Double
    ): AccountResponse {
        val account: Account = accountRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.ACCOUNT_NOT_FOUND)
        account.apply {
            balance = balance.plus(amount)
            updatedAt = LocalDateTime.now()
        }
        accountRepository.save(account)
        return toResponseDTO(account)
    }

    fun transactionAmount(
        id: UUID,
        transactionRequest: TransactionRequest
    ): AccountResponse {
        if (id == transactionRequest.recipient) throw BankException(ErrorCode.SAME_ACCOUNT)
        val payer: Account = accountRepository.findByIdOrNull(id) ?: throw BankException(ErrorCode.ACCOUNT_NOT_FOUND)
        val recipient: Account = accountRepository.findByIdOrNull(transactionRequest.recipient) ?: throw BankException(
            ErrorCode.ACCOUNT_NOT_FOUND
        )

        if (payer.balance - transactionRequest.amount < 0.0) throw BankException(ErrorCode.INSUFFICIENT_FUNDS)
        payer.apply {
            balance = payer.balance.minus(transactionRequest.amount)
            updatedAt = LocalDateTime.now()
        }
        accountRepository.save(payer)
        recipient.apply {
            balance = recipient.balance.plus(transactionRequest.amount)
            updatedAt = LocalDateTime.now()
        }
        accountRepository.save(recipient)
        return toResponseDTO(payer)
    }

    private fun toResponseDTO(account: Account): AccountResponse {
        return AccountResponse(
            id = account.id,
            balance = account.balance,
            type = account.type.toString(),
            createdAt = account.createdAt.toString(),
            updatedAt = account.updatedAt.toString(),
        )
    }
}