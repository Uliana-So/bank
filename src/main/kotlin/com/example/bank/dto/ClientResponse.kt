package com.example.bank.dto

import java.util.*


data class ClientResponse(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val phone: String,
    val uid: UUID?,
    val createdAt: String,
    val updatedAt: String,
)