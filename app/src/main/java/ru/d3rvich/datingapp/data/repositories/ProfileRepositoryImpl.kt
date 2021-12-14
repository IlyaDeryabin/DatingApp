package ru.d3rvich.datingapp.data.repositories

import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun getProfileBy(id: Long): ProfileEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfile(): ProfileEntity {
        // TODO: 13.12.2021 Реализовать получение данных профиля по токену
        return ProfileEntity(
            name = "Василий",
            city = "Тарко-сале",
            birthday = DateEntity(0, 0, 0),
            description = "Не убу вообше што тут писать",
            zodiacId = 0,
            fateNumber = 0,
            socionicTypeNumber = 0,
            personalitiesNumber = 0,
            imageLink = ""
        )
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean {
        return true
    }
}