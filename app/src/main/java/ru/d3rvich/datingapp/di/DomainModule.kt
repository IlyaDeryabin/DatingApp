package ru.d3rvich.datingapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.domain.interactor.DatingInteractorImpl
import ru.d3rvich.datingapp.domain.repository.DatingRepository

/**
 * Dagger модюль для domain слоя
 * */
@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideDatingInteractor(repository: DatingRepository): DatingInteractor {
        return DatingInteractorImpl(repository = repository)
    }
}