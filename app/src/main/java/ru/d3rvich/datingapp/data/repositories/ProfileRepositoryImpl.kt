package ru.d3rvich.datingapp.data.repositories

import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun getProfileBy(id: Long): ProfileEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfile(): ProfileEntity {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean {
        return true
    }
}