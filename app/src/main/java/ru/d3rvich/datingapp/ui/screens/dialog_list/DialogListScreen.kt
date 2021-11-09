package ru.d3rvich.datingapp.ui.screens.dialog_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screens
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListAction
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListEvent
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListViewState
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListLoading
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewDisplay
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewError
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewNoItems

@ExperimentalCoilApi
@Composable
fun DialogListScreen(navController: NavController, viewModel: DialogListViewModel) {
    when (val viewState = viewModel.dialogListViewState.value) {
        DialogListViewState.Idle -> {
        }
        is DialogListViewState.DialogList -> DialogListViewDisplay(dialogs = viewState.dialogs,
            onItemClicked = { dialogId ->
                viewModel.obtainEvent(DialogListEvent.DialogSelected(dialogId))
            })
        DialogListViewState.EmptyList -> DialogListViewNoItems()
        is DialogListViewState.Error -> DialogListViewError {
            viewModel.obtainEvent(DialogListEvent.LoadContent)
        }
        DialogListViewState.Loading -> DialogListLoading()
    }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(DialogListEvent.EnterScreen)
        viewModel.dialogListAction.collect { action ->
            when (action) {
                DialogListAction.NavigateToPairMatch -> {
                    navController.navigate("empty")
                }
                is DialogListAction.NavigateToDialog -> {
                    navController.navigate(Screens.DialogScreen.route + "/${action.dialogId}")
                }
            }
        }
    }
}