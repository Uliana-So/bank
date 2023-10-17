package com.example.bank.controller

import com.example.bank.dto.ClientRequest
import com.example.bank.dto.ClientResponse
import com.example.bank.dto.SuccessResponse
import com.example.bank.entity.Client
import com.example.bank.service.ClientService
import org.springframework.web.bind.annotation.*
import java.util.*
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity
import kotlin.collections.List


@RestController
@RequestMapping("/api/user")
class ClientController(val clientService: ClientService) {

    @GetMapping("/list")
    fun list(): SuccessResponse<List<ClientResponse>> {
        val clientList: List<ClientResponse> = clientService.getAll()
        return SuccessResponse(
            status = "SUCCESS",
            data = clientList
        )
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) : SuccessResponse<ClientResponse> {
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

//    @PutMapping("/{id}")
//    fun update(@PathVariable id: Long) = clientService.update(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = clientService.remove(id)
}