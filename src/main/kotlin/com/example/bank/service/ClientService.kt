package com.example.bank.service

import com.example.bank.dto.ClientRequest
import com.example.bank.dto.ClientResponse
import com.example.bank.entity.Client
import com.example.bank.repository.ClientRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.List

@Service
class ClientService(val clientRepository: ClientRepository) {
    fun getAll(): List<ClientResponse> {
        val clientResponse: MutableIterable<Client> = clientRepository.findAll()
        return clientResponse.map { toResponseDTO(it) }
    }

    fun getId(id: UUID): ClientResponse {
        val clientResponse: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        return toResponseDTO(clientResponse)
    }

    fun add(client: ClientRequest): ClientResponse {
        val newClient: Client = clientRepository.save(client.convertToSave())
        return toResponseDTO(newClient)
    }

    fun update(
        id: UUID,
        client: ClientRequest
    ): ClientResponse {
        val updateClient: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        updateClient.apply {
            firstName = client.firstName
            lastName = client.lastName
            middleName = client.middleName
            phone = client.phone
            updatedAt = LocalDateTime.now()
        }
        clientRepository.save(updateClient)
        return toResponseDTO(updateClient)
    }

    fun remove(id: UUID) {
        val client: Client = clientRepository.findByIdOrNull(id) ?: throw ChangeSetPersister.NotFoundException()
        clientRepository.delete(client)
    }

    private fun toResponseDTO(client: Client): ClientResponse {
        return ClientResponse(
            id = client.id,
            firstName = client.firstName,
            lastName = client.lastName,
            middleName = client.middleName,
            phone = client.phone,
            createdAt = client.createdAt.toString(),
            updatedAt = client.updatedAt.toString(),
        )
    }
}