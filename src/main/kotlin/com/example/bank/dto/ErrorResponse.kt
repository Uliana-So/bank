package com.example.bank.dto

data class ErrorResponse(
    val status: String,
    val error: String,
    val details: Any? = null
)