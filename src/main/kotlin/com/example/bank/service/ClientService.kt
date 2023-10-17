package com.example.bank.service

import com.example.bank.dto.ClientRequest
import com.example.bank.dto.ClientResponse
import com.example.bank.dto.SuccessResponse
import com.example.bank.entity.Client
import com.example.bank.repository.ClientRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.List

@Service
class ClientService(val clientRepository: ClientRepository) {
    fun getAll(): List<ClientResponse> {
        val clientResponse: MutableIterable<Client> = clientRepository.findAll()
        return clientResponse.map { toResponseDTO(it) }
    }

    fun getId(id: Long): ClientResponse {
        val clientResponse: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        return toResponseDTO(clientResponse)
    }

    fun add(client: ClientRequest): ClientResponse {
        val newClient : Client = clientRepository.save(client.convertToSave())
        return toResponseDTO(newClient)
    }

    fun remove(id: Long): ResponseEntity<Void>? {
        return clientRepository.findById(id).map {
            clientRepository.deleteById(id)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    private fun toResponseDTO(client: Client): ClientResponse {
        return ClientResponse(
            firstName = client.firstName,
            lastName = client.lastName,
            middleName = client.middleName,
            phone = client.phone,
            uid = client.uid,
            createdAt = client.createdAt.toString(),
            updatedAt = client.updatedAt.toString(),
        )
    }
}