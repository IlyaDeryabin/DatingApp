package ru.d3rvich.datingapp.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.d3rvich.datingapp.domain.entity.*

/**
 * Класс бизнес-логики приложения
 * */
interface DatingInteractor {

    suspend fun performLogin(authEntity: AuthEntity): Boolean

    suspend fun performSignUp(authEntity: AuthEntity): Boolean

    suspend fun getDialogList(): List<DialogListItemEntity>

    suspend fun getDialogBy(id: String): DialogEntity

    suspend fun sendMessage(id: String, messageEntity: MessageEntity)

    suspend fun getDialogFlow(dialogId: String): Flow<MessageEntity>

    suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean

    suspend fun getCandidatesForPairList(): List<Int>

    suspend fun getUserProfileBy(id: String): ProfileEntity

    suspend fun getUserProfile(): ProfileEntity
}