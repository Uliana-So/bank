package com.example.bank.dto

data class ErrorResponse<T>(

    val code: Int,

    val status: String,

    val error: T
)