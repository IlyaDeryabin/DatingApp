package ru.d3rvich.datingapp.ui.screens.dialog_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.DrawerScreen
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.navigation.Router
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListAction
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListEvent
import ru.d3rvich.datingapp.ui.screens.dialog_list.models.DialogListViewState
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListLoading
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewDisplay
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewError
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.DialogListViewNoItems

@ExperimentalCoilApi
@Composable
fun DialogListScreen(
    router: Router? = null,
    openDrawer: () -> Unit,
    dialogListViewModel: DialogListViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = DrawerScreen.DialogListScreen.titleResId))
            }, navigationIcon = {
                IconButton(onClick = { openDrawer() }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Open drawer")
                }
            })
        }) {
        when (val viewState = dialogListViewModel.dialogListViewState.value) {
            DialogListViewState.Idle -> {
            }
            is DialogListViewState.DialogList -> DialogListViewDisplay(dialogs = viewState.dialogs,
                onItemClicked = { dialogId ->
                    dialogListViewModel.obtainEvent(DialogListEvent.DialogSelected(dialogId))
                })
            DialogListViewState.EmptyList -> DialogListViewNoItems()
            is DialogListViewState.Error -> DialogListViewError {
                dialogListViewModel.obtainEvent(DialogListEvent.LoadContent)
            }
            DialogListViewState.Loading -> DialogListLoading()
        }
    }

    LaunchedEffect(Unit) {
        dialogListViewModel.obtainEvent(DialogListEvent.EnterScreen)
        dialogListViewModel.dialogListAction.collect { action ->
            when (action) {
                DialogListAction.NavigateToPairMatch -> {
                    router?.routeTo(DrawerScreen.PairSearchScreen.route)
                }
                is DialogListAction.NavigateToDialog -> {
                    router?.routeTo(Screen.DialogScreen.route + "/${action.dialogId}")
                }
            }
        }
    }
}