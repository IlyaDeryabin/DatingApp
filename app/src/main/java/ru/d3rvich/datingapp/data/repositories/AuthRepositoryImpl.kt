package ru.d3rvich.datingapp.data.repositories

import ru.d3rvich.datingapp.data.mappers.toAuthDto
import ru.d3rvich.datingapp.data.services.AuthService
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.utils.AuthResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun performLogin(authEntity: AuthEntity): AuthResult {
        return authService.login(authEntity.toAuthDto())
    }

    override suspend fun registerNewUser(authEntity: AuthEntity): AuthResult {
        return authService.signup(authEntity.toAuthDto())
    }
}