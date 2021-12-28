package ru.d3rvich.datingapp.domain.repositories

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.utils.AuthResult

/**
 * Класс для работы с данными
 * */
interface AuthRepository {
    suspend fun performLogin(authEntity: AuthEntity): AuthResult

    suspend fun registerNewUser(authEntity: AuthEntity): AuthResult
}