package com.example.bank.dto


data class SuccessResponse<T>(
    val status: String,
    val data: T
)
