package ru.d3rvich.datingapp.ui.screens.dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogAction
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogEvent
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogViewState
import javax.inject.Inject

const val DIALOG_ID_KEY = "dialog_id"

@HiltViewModel
class DialogViewModel @Inject constructor(
    private val interactor: DatingInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), EventHandler<DialogEvent> {
    private val _viewState = mutableStateOf<DialogViewState>(DialogViewState.Loading)
    val viewState: State<DialogViewState>
        get() = _viewState

    private val _action = MutableSharedFlow<DialogAction>()
    val action: SharedFlow<DialogAction> = _action.asSharedFlow()

    private val dialogId: String = savedStateHandle.get<String>(DIALOG_ID_KEY)
        ?: error("Параметр Dialog ID не был добавлен в BackStackEntry")

    init {
        collectDialog()
    }

    override fun obtainEvent(event: DialogEvent) {
        when (val currentState = _viewState.value) {
            is DialogViewState.Loading -> {
                reduce(event, currentState)
            }
            is DialogViewState.Error -> {
                reduce(event, currentState)
            }
            is DialogViewState.Dialog -> {
                reduce(event, currentState)
            }
        }
    }

    private fun collectDialog() {
        viewModelScope.launch {
            _viewState.value = DialogViewState.Loading
            try {
                val dialog = interactor.getDialogBy(dialogId)
                _viewState.value = DialogViewState.Dialog(dialog)
            } catch (e: Exception) {
                _viewState.value = DialogViewState.Error("")
            }
        }
    }

    private fun reduce(event: DialogEvent, state: DialogViewState.Loading) {
        when (event) {
            DialogEvent.BackPressed -> {
                setAction(DialogAction.PopBackStack)
            }
            else -> {
                error("Illegal $event for this $state.")
            }
        }
    }

    private fun reduce(event: DialogEvent, state: DialogViewState.Error) {
        when (event) {
            DialogEvent.ReloadData -> collectDialog()
            DialogEvent.BackPressed -> {
                setAction(DialogAction.PopBackStack)
            }
            else -> {
                error("Illegal $event for this $state.")
            }
        }
    }

    private fun reduce(event: DialogEvent, state: DialogViewState.Dialog) {
        when (event) {
            is DialogEvent.SendMessage -> {
                val dialog = state.dialog
                val messages = dialog.messages.toMutableList()
                messages.add(event.message)
                _viewState.value = state.copy(dialog = dialog.copy(messages = messages))
            }
            DialogEvent.CompanionClicked -> {
                setAction(DialogAction.NavigateToCompanion)
            }
            DialogEvent.BackPressed -> {
                setAction(DialogAction.PopBackStack)
            }
            else -> error("Illegal $event for this $state.")
        }
    }

    private fun setAction(action: DialogAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }
}