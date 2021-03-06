package ru.d3rvich.datingapp.ui.screens.dialog.models

import ru.d3rvich.datingapp.domain.entity.DialogEntity

sealed class DialogViewState {
    object Loading : DialogViewState()
    data class Dialog(val dialog: DialogEntity) : DialogViewState()
    class Error(val message: String) : DialogViewState()
}
