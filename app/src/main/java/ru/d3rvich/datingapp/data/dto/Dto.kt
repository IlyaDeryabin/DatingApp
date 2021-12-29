package ru.d3rvich.datingapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object классы data слоя
 * */
@Serializable
data class AuthDto(
    @SerialName("phone") val phoneNumber: String,
    val password: String
)

@Serializable
data class AuthResponse(@SerialName("access_token") val accessToken: String)