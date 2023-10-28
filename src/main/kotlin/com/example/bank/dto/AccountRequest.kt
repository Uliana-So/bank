package com.example.bank.dto

import com.example.bank.entity.TypeAccount

data class AccountRequest(
    val type: TypeAccount
)