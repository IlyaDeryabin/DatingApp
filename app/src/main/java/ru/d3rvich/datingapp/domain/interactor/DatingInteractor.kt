package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

/**
 * Класс бизнес-логики приложения
 * */
interface DatingInteractor {

    suspend fun performLogin(authEntity: AuthEntity): Boolean

    suspend fun performSignUp(authEntity: AuthEntity): Boolean

    suspend fun getDialogList(): List<DialogListItemEntity>

    suspend fun getDialogBy(id: String): DialogEntity

    suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean

    suspend fun getUserProfile(): ProfileEntity
}