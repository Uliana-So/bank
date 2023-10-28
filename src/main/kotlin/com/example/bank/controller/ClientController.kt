package com.example.bank.controller

import com.example.bank.dto.*
import com.example.bank.service.AccountService
import com.example.bank.service.ClientService
import org.springframework.web.bind.annotation.*
import java.util.*
import jakarta.validation.Valid;
import kotlin.collections.List

@RestController
@RequestMapping("/api/user")
class ClientController(
    val clientService: ClientService,
    val accountService: AccountService
) {
    @GetMapping("/list")
    fun list(): SuccessResponse<List<ClientResponse>> {
        val clientList: List<ClientResponse> = clientService.getAll()
        return SuccessResponse(
            status = "SUCCESS",
            data = clientList
        )
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: UUID) : SuccessResponse<ClientResponse> {
        val client: ClientResponse = clientService.getId(id)
        return SuccessResponse(
            status = "SUCCESS",
            data = client
        )
    }

    @PostMapping
    fun create(@Valid @RequestBody client : ClientRequest): SuccessResponse<ClientResponse> {
        val newClient = clientService.add(client)
        return SuccessResponse(
            status = "SUCCESS",
            data = newClient
        )
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody client : ClientRequest
    ):SuccessResponse<ClientResponse>{
        val updateClient = clientService.update(
            id = id,
            client = client
        )
        return SuccessResponse(
            status = "SUCCESS",
            data = updateClient
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): SuccessResponse<UUID> {
        clientService.remove(id)
        return SuccessResponse(
            status = "SUCCESS",
            data = id
        )
    }

    @GetMapping("/{id}/account/list")
    fun getAccountList(@PathVariable id: UUID) : SuccessResponse<List<UUID>> {
        val accounts: List<UUID> = accountService.getListByClient(id)
        return SuccessResponse(
            status = "SUCCESS",
            data = accounts
        )
    }

    @PostMapping("/{id}/account/open")
    fun openAccount(
        @PathVariable id: UUID,
        @Valid @RequestBody typeRequest : AccountRequest
    ) : SuccessResponse<AccountResponse> {
        val account: AccountResponse = accountService.addByClient(
            id = id,
            type = typeRequest.type
        )
        return SuccessResponse(
            status = "SUCCESS",
            data = account
        )
    }
}