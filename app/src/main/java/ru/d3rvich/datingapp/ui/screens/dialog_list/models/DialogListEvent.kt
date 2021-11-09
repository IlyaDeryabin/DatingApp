package ru.d3rvich.datingapp.ui.screens.dialog_list.models

sealed class DialogListEvent {
    object EnterScreen : DialogListEvent()
    object LoadContent : DialogListEvent()
    object PairMatchButtonClicked : DialogListEvent()
    class DialogSelected(val dialogId: String) : DialogListEvent()
}
