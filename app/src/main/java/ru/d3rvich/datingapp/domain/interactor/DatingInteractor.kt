package ru.d3rvich.datingapp.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.d3rvich.datingapp.domain.entity.*
import ru.d3rvich.datingapp.domain.utils.AuthResult

/**
 * Класс бизнес-логики приложения
 * */
interface DatingInteractor {

    suspend fun performLogin(authEntity: AuthEntity): AuthResult

    suspend fun performSignUp(authEntity: AuthEntity): AuthResult

    suspend fun getDialogList(): List<DialogListItemEntity>

    suspend fun getDialogBy(id: String): DialogEntity

    suspend fun sendMessage(id: String, messageEntity: MessageEntity)

    suspend fun getDialogFlow(dialogId: String) : Flow<MessageEntity>

    suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean

    suspend fun getUserProfile(): ProfileEntity
}