package ru.d3rvich.datingapp.domain.repositories

import ru.d3rvich.datingapp.domain.entity.ProfileEntity

interface ProfileRepository {
    suspend fun getProfileBy(id: String): ProfileEntity

    suspend fun getUserProfile(): ProfileEntity

    suspend fun saveUserProfile(profileEntity: ProfileEntity): Boolean
}