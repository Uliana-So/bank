package com.example.bank.dto

import com.example.bank.entity.Client
import jakarta.validation.constraints.*

data class ClientRequest(
    @field:NotBlank(message = "{validation.field.firstName.blank}")
    val firstName: String,

    @field:NotBlank(message = "{validation.field.lastName.blank}")
    val lastName: String,
    val middleName: String = "",

    @field:Pattern(regexp = "^79[0-9]{9}$")
    @field:NotBlank(message = "{validation.field.phone.blank}")
    val phone: String,
) {
    fun convertToSave(): Client {
        return Client(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            phone = phone
        )
    }
}