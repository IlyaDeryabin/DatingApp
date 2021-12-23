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
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
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

    private var dialogs: List<DialogListItemEntity> = emptyList()
        get() {
            return when {
                field.isEmpty() -> {
                    error("Список не должен быть пустым на момент вызова геттера")
                }
                else -> {
                    field
                }
            }
        }

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
            is DialogListViewState.Search -> {
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
            else -> {
                error("Unexpected $event for $state.")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Loading) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.EmptyList) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.DialogList) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            is DialogListEvent.DialogSelected -> performDialogSelected(event.dialogId)
            DialogListEvent.SearchButtonClicked -> {
                dialogs = state.dialogs
                _dialogListViewState.value = DialogListViewState.Search(
                    text = "",
                    dialogs = dialogs
                )
            }
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Search) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            is DialogListEvent.OnSearchFieldChanged -> {
                val filteredDialogs =
                    dialogs.filter { event.text.lowercase() in it.userName.lowercase() }
                _dialogListViewState.value =
                    DialogListViewState.Search(text = event.text, dialogs = filteredDialogs)
            }
            DialogListEvent.OnCloseSearch -> {
                _dialogListViewState.value = DialogListViewState.DialogList(dialogs)
                dialogs = emptyList()
            }
            is DialogListEvent.DialogSelected -> performDialogSelected(event.dialogId)
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: DialogListEvent, state: DialogListViewState.Error) {
        when (event) {
            DialogListEvent.EnterScreen -> return
            is DialogListEvent.ReloadContent -> fetchDialogs()
            else -> {
                error("Unexcited $event for $state")
            }
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
