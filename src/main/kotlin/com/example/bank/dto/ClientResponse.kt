package com.example.bank.dto

import java.util.*

data class ClientResponse(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val phone: String,
    val createdAt: String,
    val updatedAt: String,
)