package com.example.bank.controller

import com.example.bank.dto.AccountResponse
import com.example.bank.dto.SuccessResponse
import com.example.bank.service.AccountService
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
}