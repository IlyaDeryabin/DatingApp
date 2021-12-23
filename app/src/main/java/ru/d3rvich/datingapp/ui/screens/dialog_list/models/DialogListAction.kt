package ru.d3rvich.datingapp.ui.screens.dialog_list.models

sealed class DialogListAction {
    class NavigateToDialog(val dialogId: String) : DialogListAction()
}
