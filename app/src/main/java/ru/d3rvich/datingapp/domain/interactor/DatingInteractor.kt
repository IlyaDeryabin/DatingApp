package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.LoginEntity

/**
 * Класс бизнес-логики приложения
 * */
interface DatingInteractor {

    suspend fun performLogin(loginEntity: LoginEntity): Boolean
}