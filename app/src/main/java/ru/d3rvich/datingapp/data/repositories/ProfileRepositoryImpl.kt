package ru.d3rvich.datingapp.data.repositories

import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun getProfileBy(id: String): ProfileEntity {
        // TODO: 24.12.2021 Реализовать получение пользователя по id
        return ProfileEntity(
            name = "Роман",
            city = "Екатеринбург",
            age = 22,
            birthday = DateEntity(day = 24, month = 12, year = 1999),
            description = "Здесь должно быть описание, но мне лень его придумывать. Поверьте, я хороший.",
            zodiacId = 4,
            fateNumber = 8,
            socionicTypeNumber = 1,
            personalitiesNumber = 1,
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Zunge_raus.JPG/1200px-Zunge_raus.JPG"
        )
    }

    override suspend fun getUserProfile(): ProfileEntity {
        // TODO: 13.12.2021 Реализовать получение данных профиля по токену
        return ProfileEntity(
            name = "Василий",
            city = "Название города",
            age = 0,
            birthday = DateEntity(0, 0, 0),
            description = "Не убу вообше што тут писать",
            zodiacId = 0,
            fateNumber = 0,
            socionicTypeNumber = 0,
            personalitiesNumber = 0,
            imageLink = "https://static.probusiness.io/n/03/d/38097027_439276526579800_2735888197547458560_n.jpg"
        )
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean {
        return true
    }
}