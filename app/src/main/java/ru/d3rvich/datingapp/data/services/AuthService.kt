package ru.d3rvich.datingapp.data.services

import ru.d3rvich.datingapp.data.dto.AuthDto

interface AuthService {
    suspend fun login(authDto: AuthDto)

    suspend fun signup(authDto: AuthDto)
}