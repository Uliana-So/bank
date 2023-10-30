package com.example.bank.exception

import org.springframework.http.HttpStatus

class BankException(
    val errorCode: ErrorCode
) : RuntimeException()

enum class ErrorCode(val status: HttpStatus, val message: String) {
    CLIENT_NOT_FOUND(HttpStatus.NOT_FOUND,"Client not found"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND,"Account not found"),
    SAME_ACCOUNT(HttpStatus.BAD_REQUEST,"Cannot be transferred to the same account"),
    INSUFFICIENT_FUNDS(HttpStatus.BAD_REQUEST,"Insufficient funds"),
    ;
}