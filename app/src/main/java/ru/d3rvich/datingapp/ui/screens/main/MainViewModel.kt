package ru.d3rvich.datingapp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.screens.main.models.MainScreenState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: DatingInteractor) : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        loadingUserProfile()
    }

    /**
    Инициирует загрузку профиля пользователя и изменение значения поля [state].
     */
    fun loadingUserProfile() {
        viewModelScope.launch {
            try {
                val profile = interactor.getUserProfile()
                _state.emit(MainScreenState.Loaded(profile))
            } catch (e: Exception) {
                _state.emit(MainScreenState.Error(R.string.loading_error_message))
            }
        }
    }
}