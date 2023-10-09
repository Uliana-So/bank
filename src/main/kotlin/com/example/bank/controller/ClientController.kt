package com.example.bank.controller

import com.example.bank.model.Client
import com.example.bank.repository.ClientRepository
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import java.util.*
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
class ClientController(private val clientRepository: ClientRepository) {

    @GetMapping("/list")
    fun list() = clientRepository.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<Client> {
        return clientRepository.findById(id).map { client ->
            ResponseEntity.ok(client)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun create(@Valid @RequestBody client : Client) = clientRepository.save(client)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        return clientRepository.findById(id).map {
            clientRepository.deleteById(id)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}