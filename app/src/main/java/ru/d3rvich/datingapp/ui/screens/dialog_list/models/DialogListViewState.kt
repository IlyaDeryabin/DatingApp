package ru.d3rvich.datingapp.ui.screens.dialog_list.models

import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity

sealed class DialogListViewState {
    object Idle : DialogListViewState()
    object Loading : DialogListViewState()
    object EmptyList : DialogListViewState()
    data class Search(val text: String, val dialogs: List<DialogListItemEntity>) :
        DialogListViewState()
    class DialogList(val dialogs: List<DialogListItemEntity>) : DialogListViewState()
    class Error(val message: String) : DialogListViewState()
}