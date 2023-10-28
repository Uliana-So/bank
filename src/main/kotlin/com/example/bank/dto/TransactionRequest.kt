package com.example.bank.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.UUID

data class TransactionRequest(
    @field:Positive
    @field:NotNull(message = "{validation.field.amount.notNull}")
    val amount: Double,

    @field:NotNull(message = "{validation.field.recipient.notNull}")
    val recipient: UUID
)

data class RefillRequest(
    @field:Positive
    @field:NotNull(message = "{validation.field.amount.notNull}")
    val amount: Double,
)