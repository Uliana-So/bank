package com.example.bank.entity

import java.time.LocalDateTime
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.util.UUID

@Entity
@Table(name = "client")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),

    @NotBlank
    @Column(name = "first_name")
    var firstName: String,

    @NotBlank
    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "middle_name")
    var middleName: String = "",

    @NotBlank
    @Column(name = "phone")
    var phone: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)