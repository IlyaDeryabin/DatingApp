package ru.d3rvich.datingapp.domain.repositories

import ru.d3rvich.datingapp.domain.entity.LoginEntity

/**
 * Класс для работы с данными
 * */
interface AuthRepository {
    suspend fun performLogin(loginEntity: LoginEntity): Boolean

    suspend fun registerNewUser()
}