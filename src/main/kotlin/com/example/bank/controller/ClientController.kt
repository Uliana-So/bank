package com.example.bank.controller

import jakarta.validation.Valid;

import com.example.bank.model.Client
import com.example.bank.repository.ClientRepository
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class ClientController(private val clientRepository: ClientRepository) {

    @GetMapping("/list")
    fun list() = clientRepository.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = clientRepository.findById(id) ?: throw ResponseStatusException(NOT_FOUND,"")

    @PostMapping
    fun create(@Valid @RequestBody client : Client) = clientRepository.save(client)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = clientRepository.deleteById(id)
}