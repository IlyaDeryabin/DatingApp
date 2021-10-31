package ru.d3rvich.datingapp.domain.interactor

import ru.d3rvich.datingapp.domain.repository.DatingRepository
import javax.inject.Inject

class DatingInteractorImpl @Inject constructor(private val repository: DatingRepository) :
    DatingInteractor