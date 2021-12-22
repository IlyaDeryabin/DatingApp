package ru.d3rvich.datingapp.ui.screens.dialog.models

sealed class DialogAction {
    object PopBackStack : DialogAction()
    object NavigateToCompanion : DialogAction()
}
