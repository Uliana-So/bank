package com.example.bank.dto

import java.util.*

data class AccountResponse(
    val id: UUID,
    val balance: Double,
    val type: String,
    val createdAt: String,
    val updatedAt: String,
)