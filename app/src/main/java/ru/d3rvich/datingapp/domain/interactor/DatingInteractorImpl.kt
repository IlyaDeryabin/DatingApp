package ru.d3rvich.datingapp.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.d3rvich.datingapp.domain.entity.*
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.repositories.DialogRepository
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository
import ru.d3rvich.datingapp.domain.utils.AuthResult
import javax.inject.Inject

class DatingInteractorImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val dialogRepository: DialogRepository,
    private val profileRepository: ProfileRepository
) : DatingInteractor {

    override suspend fun performLogin(authEntity: AuthEntity): AuthResult {
        return authRepository.performLogin(authEntity)
    }

    override suspend fun performSignUp(authEntity: AuthEntity): AuthResult {
        return authRepository.registerNewUser(authEntity)
    }

    override suspend fun getDialogList(): List<DialogListItemEntity> {
        return dialogRepository.getDialogList()
    }

    override suspend fun getDialogBy(id: String): DialogEntity {
        return dialogRepository.getDialogBy(id)
    }

    override suspend fun sendMessage(id: String, messageEntity: MessageEntity) {
        dialogRepository.sendMessage(dialogId = id, messageEntity = messageEntity)
    }

    override suspend fun getDialogFlow(dialogId: String): Flow<MessageEntity> {
        return dialogRepository.getDialogFlow(dialogId = dialogId)
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean {
        return profileRepository.saveUserProfile(profileEntity)
    }

    override suspend fun getUserProfile(): ProfileEntity {
        return profileRepository.getUserProfile()
    }
}