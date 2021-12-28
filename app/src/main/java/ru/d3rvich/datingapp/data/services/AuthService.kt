package ru.d3rvich.datingapp.data.services

import ru.d3rvich.datingapp.data.dto.AuthDto
import ru.d3rvich.datingapp.domain.utils.AuthResult

interface AuthService {
    suspend fun login(authDto: AuthDto): AuthResult

    suspend fun signup(authDto: AuthDto): AuthResult
}