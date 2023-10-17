package com.example.bank.entity

import java.time.LocalDateTime
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.util.UUID

@Entity
@Table(name = "client")
data class Client(
    @NotBlank
    @Column(name = "firstName")
    var firstName: String,

    @NotBlank
    @Column(name = "lastName")
    var lastName: String,

    @Column(name = "middleName")
    var middleName: String = "",

    @NotBlank
    @Column(name = "phone")
    var phone: String,

    @Column(name = "uid")
    var uid: UUID = UUID.randomUUID(),

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
)