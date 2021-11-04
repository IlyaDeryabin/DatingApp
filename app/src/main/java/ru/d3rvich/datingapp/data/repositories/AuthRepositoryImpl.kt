package ru.d3rvich.datingapp.data.repositories

import kotlinx.coroutines.delay
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository

class AuthRepositoryImpl: AuthRepository {
    override suspend fun performLogin(authEntity: AuthEntity): Boolean {
        delay(1500)
        return true
    }

    override suspend fun registerNewUser(authEntity: AuthEntity): Boolean {
        delay(1500)
        return true
    }
}