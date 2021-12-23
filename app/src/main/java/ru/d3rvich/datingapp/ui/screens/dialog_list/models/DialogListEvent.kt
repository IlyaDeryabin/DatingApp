package ru.d3rvich.datingapp.ui.screens.dialog_list.models

sealed class DialogListEvent {
    object EnterScreen : DialogListEvent()
    object ReloadContent : DialogListEvent()
    object SearchButtonClicked : DialogListEvent()
    object OnCloseSearch : DialogListEvent()
    class OnSearchFieldChanged(val text: String) : DialogListEvent()
    class DialogSelected(val dialogId: String) : DialogListEvent()
}