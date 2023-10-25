package com.example.bank.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "account")
data class Account(

    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "balance")
    var balance: Double = 0.0,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    var client: Client,
)