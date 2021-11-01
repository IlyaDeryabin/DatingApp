package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.LoginEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import javax.inject.Inject

class DatingInteractorImpl @Inject constructor(private val authRepository: AuthRepository) :
    DatingInteractor {

    override suspend fun performLogin(loginEntity: LoginEntity): Boolean {
        return authRepository.performLogin(loginEntity)
    }
}