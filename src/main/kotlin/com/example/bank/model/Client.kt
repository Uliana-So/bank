package com.example.bank.model

import java.time.LocalDateTime
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
data class Client(
    @NotBlank(message = "First name is mandatory")
    var firstName: String = "",

    @NotBlank(message = "Last name is mandatory")
    var lastName: String = "",

    var middleName: String = "",

    @NotBlank(message = "Phone is mandatory")
    var phone: String = "",

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
)