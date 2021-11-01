package ru.d3rvich.datingapp.data.repositories

import kotlinx.coroutines.delay
import ru.d3rvich.datingapp.domain.entity.LoginEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository

class AuthRepositoryImpl: AuthRepository {
    override suspend fun performLogin(loginEntity: LoginEntity): Boolean {
        delay(1000)
        return true
    }

    override suspend fun registerNewUser() {
        TODO("Not yet implemented")
    }
}