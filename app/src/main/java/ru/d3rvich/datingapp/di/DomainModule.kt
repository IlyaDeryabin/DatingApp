package ru.d3rvich.datingapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.domain.interactor.DatingInteractorImpl
import ru.d3rvich.datingapp.domain.repositories.AuthRepository
import ru.d3rvich.datingapp.domain.repositories.DialogRepository

/**
 * Dagger модюль для domain слоя
 * */
@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideDatingInteractor(
        repository: AuthRepository,
        dialogRepository: DialogRepository,
    ): DatingInteractor {
        return DatingInteractorImpl(authRepository = repository,
            dialogRepository = dialogRepository)
    }
}