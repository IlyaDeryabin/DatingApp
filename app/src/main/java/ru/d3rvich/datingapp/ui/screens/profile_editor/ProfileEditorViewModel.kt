package ru.d3rvich.datingapp.ui.screens.profile_editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorAction
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorEvent
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorViewState
import javax.inject.Inject

@HiltViewModel
class ProfileEditorViewModel @Inject constructor(private val interactor: DatingInteractor) :
    ViewModel(), EventHandler<ProfileEditorEvent> {

    private val _viewState = mutableStateOf<ProfileEditorViewState>(ProfileEditorViewState.Idle)
    val viewState: State<ProfileEditorViewState>
        get() = _viewState

    private val _action = MutableSharedFlow<ProfileEditorAction>()
    val action: SharedFlow<ProfileEditorAction> = _action.asSharedFlow()

    override fun obtainEvent(event: ProfileEditorEvent) {
        when (event) {
            is ProfileEditorEvent.OnSaveButtonClicked -> {
                viewModelScope.launch {
                    _viewState.value = ProfileEditorViewState.SaveInProgress
                    val result = interactor.saveUserProfile(event.profile)
                    if (result) {
                        _action.emit(ProfileEditorAction.NavigateToDialogList)
                    } else {
                        _viewState.value =
                            ProfileEditorViewState.Error(R.string.profile_editor_error_message)
                    }
                }
            }
        }
    }
}