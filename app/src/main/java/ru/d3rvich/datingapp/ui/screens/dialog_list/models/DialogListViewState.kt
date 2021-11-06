package ru.d3rvich.datingapp.ui.screens.dialog_list.models

sealed class DialogListViewState {
    object Loading : DialogListViewState()
    object EmptyList : DialogListViewState()
}
