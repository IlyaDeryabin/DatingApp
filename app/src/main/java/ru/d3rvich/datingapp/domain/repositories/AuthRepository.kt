package ru.d3rvich.datingapp.domain.repositories

import ru.d3rvich.datingapp.domain.entity.AuthEntity

/**
 * Класс для работы с данными
 * */
interface AuthRepository {
    suspend fun performLogin(authEntity: AuthEntity): Boolean

    suspend fun registerNewUser(authEntity: AuthEntity): Boolean
}