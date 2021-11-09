package ru.d3rvich.datingapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.d3rvich.datingapp.data.repositories.AuthRepositoryImpl
import ru.d3rvich.datingapp.data.repositories.DialogRepositoryImpl
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.repositories.DialogRepository

/**
 * Dagger модуль data слоя
 * */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    fun provideDialogRepository(): DialogRepository {
        return DialogRepositoryImpl()
    }
}