package ru.d3rvich.datingapp.ui.screens.dialog_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import ru.d3rvich.datingapp.ui.screens.dialog_list.views.*

@ExperimentalCoilApi
@Composable
fun DialogListScreen(
    router: Router? = null,
    openDrawer: () -> Unit,
    dialogListViewModel: DialogListViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (dialogListViewModel.dialogListViewState.value !is DialogListViewState.Search) {
                TopAppBar(title = {
                    Text(text = stringResource(id = DrawerScreen.DialogListScreen.titleResId))
                }, navigationIcon = {
                    IconButton(onClick = { openDrawer() }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open drawer")
                    }
                }, actions = {
                    AnimatedVisibility(
                        visible =
                        dialogListViewModel.dialogListViewState.value is DialogListViewState.DialogList
                    ) {
                        IconButton(onClick = { dialogListViewModel.obtainEvent(DialogListEvent.SearchButtonClicked) }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                })
            }
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
                dialogListViewModel.obtainEvent(DialogListEvent.ReloadContent)
            }
            DialogListViewState.Loading -> DialogListLoading()
            is DialogListViewState.Search -> DialogListViewSearch(
                text = viewState.text,
                onTextChange = { text ->
                    dialogListViewModel.obtainEvent(DialogListEvent.OnSearchFieldChanged(text))
                },
                onItemClicked = { dialogId ->
                    dialogListViewModel.obtainEvent(DialogListEvent.DialogSelected(dialogId))
                },
                onBackPressed = {
                    dialogListViewModel.obtainEvent(DialogListEvent.OnCloseSearch)
                },
                dialogs = viewState.dialogs
            )
        }
    }

    LaunchedEffect(Unit) {
        dialogListViewModel.obtainEvent(DialogListEvent.EnterScreen)
        dialogListViewModel.dialogListAction.collect { action ->
            when (action) {
                is DialogListAction.NavigateToDialog -> {
                    router?.routeTo(Screen.DialogScreen.route + "/${action.dialogId}")
                }
            }
        }
    }
}