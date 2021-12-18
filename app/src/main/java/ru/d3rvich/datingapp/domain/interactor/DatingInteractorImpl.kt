package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.repositories.DialogRepository
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository
import javax.inject.Inject

class DatingInteractorImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val dialogRepository: DialogRepository,
    private val profileRepository: ProfileRepository
) : DatingInteractor {

    override suspend fun performLogin(authEntity: AuthEntity): Boolean {
        return authRepository.performLogin(authEntity)
    }

    override suspend fun performSignUp(authEntity: AuthEntity): Boolean {
        return authRepository.registerNewUser(authEntity)
    }

    override suspend fun getDialogList(): List<DialogListItemEntity> {
        return dialogRepository.getDialogList()
    }

    override suspend fun getDialogBy(id: String): DialogEntity {
        return dialogRepository.getDialogBy(id)
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean {
        return profileRepository.saveUserProfile(profileEntity)
    }

    override suspend fun getUserProfile(): ProfileEntity {
        return profileRepository.getUserProfile()
    }
}