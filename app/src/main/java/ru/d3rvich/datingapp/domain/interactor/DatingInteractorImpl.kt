package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import javax.inject.Inject

class DatingInteractorImpl @Inject constructor(private val authRepository: AuthRepository) :
    DatingInteractor {

    override suspend fun performLogin(authEntity: AuthEntity): Boolean {
        return authRepository.performLogin(authEntity)
    }

    override suspend fun performSignUp(authEntity: AuthEntity): Boolean {
        return authRepository.registerNewUser(authEntity)
    }
}