package ru.d3rvich.datingapp.ui.screens.dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
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

    init {
        val dialogId: String = savedStateHandle.get<String>(DIALOG_ID_KEY)
            ?: error("Параметр Dialog ID не был добавлен в BackStackEntry")
        viewModelScope.launch {
            val dialog = interactor.getDialogBy(dialogId)
            _viewState.value = DialogViewState.Dialog(dialog)
        }
    }

    override fun obtainEvent(event: DialogEvent) {
        TODO("Not yet implemented")
    }
}