package ru.d3rvich.datingapp.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import ru.d3rvich.datingapp.data.constants.AuthConstants
import ru.d3rvich.datingapp.data.remote.AuthServiceImpl
import ru.d3rvich.datingapp.data.repositories.AuthRepositoryImpl
import ru.d3rvich.datingapp.data.repositories.DialogRepositoryImpl
import ru.d3rvich.datingapp.data.repositories.ProfileRepositoryImpl
import ru.d3rvich.datingapp.data.services.AuthService
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.repositories.DialogRepository
import ru.d3rvich.datingapp.domain.repositories.ProfileRepository

/**
 * Dagger модуль data слоя
 * */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideDatingRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService = authService)
    }

    @Provides
    fun provideDialogRepository(): DialogRepository {
        return DialogRepositoryImpl()
    }

    @Provides
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl()
    }

    @Provides
    fun provideAuthService(
        httpClient: HttpClient,
        authSharedPreferences: SharedPreferences
    ): AuthService {
        return AuthServiceImpl(
            httpClient = httpClient,
            authSharedPreferences = authSharedPreferences
        )
    }

    @Provides
    fun provideAuthSharedPreferences(@ApplicationContext application: Context): SharedPreferences {
        return application.getSharedPreferences(AuthConstants.SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }
}