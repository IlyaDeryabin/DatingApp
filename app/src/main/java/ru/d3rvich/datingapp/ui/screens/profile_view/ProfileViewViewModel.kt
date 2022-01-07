package ru.d3rvich.datingapp.ui.screens.profile_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.screens.profile_view.models.ProfileViewViewState
import javax.inject.Inject

@HiltViewModel
class ProfileViewViewModel @Inject constructor(private val interactor: DatingInteractor) :
    ViewModel() {
    private val _viewState = mutableStateOf<ProfileViewViewState>(ProfileViewViewState.Loading)
    val viewState: State<ProfileViewViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            val profile = interactor.getUserProfile()
            _viewState.value = ProfileViewViewState.Display(profile = profile)
        }
    }
}