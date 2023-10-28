package com.example.bank.controller

import com.example.bank.dto.AccountResponse
import com.example.bank.dto.RefillRequest
import com.example.bank.dto.SuccessResponse
import com.example.bank.dto.TransactionRequest
import com.example.bank.service.AccountService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/account")
class AccountController(val accountService: AccountService){
    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: UUID) : SuccessResponse<AccountResponse> {
        val account: AccountResponse = accountService.getId(id)
        return SuccessResponse(
            status = "SUCCESS",
            data = account
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) : SuccessResponse<UUID> {
        accountService.remove(id)
        return SuccessResponse(
            status = "SUCCESS",
            data = id
        )
    }

    @PostMapping("/{id}/refill")
    fun refill(
        @PathVariable id: UUID,
        @Valid @RequestBody refillRequest : RefillRequest
    ) : SuccessResponse<AccountResponse> {
        val account: AccountResponse = accountService.refill(
            id = id,
            amount = refillRequest.amount
        )
        return SuccessResponse(
            status = "SUCCESS",
            data = account
        )
    }

    @PostMapping("/{id}/transaction")
    fun transaction(
        @PathVariable id: UUID,
        @Valid @RequestBody transactionRequest : TransactionRequest
    ) : SuccessResponse<AccountResponse> {
        val account: AccountResponse = accountService.transactionAmount(
            id = id,
            transactionRequest = transactionRequest
        )
        return SuccessResponse(
            status = "SUCCESS",
            data = account
        )
    }
}