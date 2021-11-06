package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.AuthEntity

/**
 * Класс бизнес-логики приложения
 * */
interface DatingInteractor {

    suspend fun performLogin(authEntity: AuthEntity): Boolean

    suspend fun performSignUp(authEntity: AuthEntity): Boolean
}