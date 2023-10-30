package com.example.bank.handler

import com.example.bank.dto.ErrorResponse
import com.example.bank.exception.BankException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse> {
        return when (exception) {
            is BankException -> ResponseEntity
                .status(exception.errorCode.status)
                .body(
                    ErrorResponse(
                        status = "ERROR",
                        error = exception.errorCode.message
                    )
                )

            is HttpMessageNotReadableException,
            is MethodArgumentNotValidException -> ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponse(
                        status = "ERROR",
                        error = "Validation error",
                        details = exception.message
                    )
                )

            else -> ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ErrorResponse(
                        status = "ERROR",
                        error = "Service internal error",
                        details = exception.message
                    )
                )
        }
    }
}