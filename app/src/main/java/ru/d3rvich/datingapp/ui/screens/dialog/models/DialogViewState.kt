package ru.d3rvich.datingapp.ui.screens.dialog.models

import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.ui.model.EmptyDialogUiModel

sealed class DialogViewState {
    object Loading : DialogViewState()
    class NoMessages(val dialog: EmptyDialogUiModel) : DialogViewState()
    data class Dialog(val dialog: DialogEntity) : DialogViewState()
    class Error(val message: String) : DialogViewState()
}
