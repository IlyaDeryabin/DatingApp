package ru.d3rvich.datingapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.d3rvich.datingapp.data.DatingRepositoryImpl
import ru.d3rvich.datingapp.domain.repository.DatingRepository

/**
 * Dagger модуль data слоя
 * */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideDatingRepository(): DatingRepository {
        return DatingRepositoryImpl()
    }
}