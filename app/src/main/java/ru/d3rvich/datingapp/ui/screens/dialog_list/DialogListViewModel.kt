package ru.d3rvich.datingapp.ui.screens.dialog_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListAction
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListEvent
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListViewState
import javax.inject.Inject

@HiltViewModel
class DialogListViewModel @Inject constructor(private val interactor: DatingInteractor) :
    ViewModel(), EventHandler<DialogListEvent> {

    private val _dialogListViewState = mutableStateOf<DialogListViewState>(DialogListViewState.Idle)
    val dialogListViewState: State<DialogListViewState> = _dialogListViewState

    private val _dialogListAction = MutableSharedFlow<DialogListAction>()
    val dialogListAction: SharedFlow<DialogListAction> = _dialogListAction.asSharedFlow()

    override fun obtainEvent(event: DialogListEvent) {
        when (val currentState = _dialogListViewState.value) {
            is DialogListViewState.Idle -> {
                reduce(event, currentState)
            }
            is DialogListViewState.Loading -> {
                reduce(event, currentState)
            }
            is DialogListViewState.EmptyList -> {
                reduce(event, currentState)
            }
            is DialogListViewState.DialogList -> {
                reduce(event, currentState)
            }
            is DialogListViewState.Error -> {
                reduce(event, currentState)
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Idle) {
        when (event) {
            is DialogListEvent.EnterScreen -> fetchDialogs()
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Loading) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            else -> {
                throw NotImplementedError("Unexcited $event for $state")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.EmptyList) {
        when (event) {
            DialogListEvent.EnterScreen -> fetchDialogs()
            is DialogListEvent.PairMatchButtonClicked -> {
                viewModelScope.launch {
                    _dialogListAction.emit(DialogListAction.NavigateToPairMatch)
                }
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.DialogList) {
        when (event) {
            is DialogListEvent.DialogSelected -> performDialogSelected(event.dialogId)
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Error) {
        when (event) {
            is DialogListEvent.LoadContent -> fetchDialogs()
        }
    }

    private fun fetchDialogs() {
        viewModelScope.launch {
            _dialogListViewState.value = DialogListViewState.Loading
            val list = interactor.getDialogList()
            if (list.isEmpty()) {
                _dialogListViewState.value = DialogListViewState.EmptyList
            } else {
                _dialogListViewState.value = DialogListViewState.DialogList(list)
            }
        }
    }

    private fun performDialogSelected(dialogId: String) {
        viewModelScope.launch {
            _dialogListAction.emit(DialogListAction.NavigateToDialog(dialogId))
        }
    }
}
