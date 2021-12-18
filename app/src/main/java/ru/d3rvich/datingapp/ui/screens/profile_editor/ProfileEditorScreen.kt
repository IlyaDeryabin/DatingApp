package ru.d3rvich.datingapp.ui.screens.profile_editor

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorAction
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorEvent
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorViewState
import ru.d3rvich.datingapp.ui.screens.profile_editor.views.ProfileEditorViewDisplay

@ExperimentalMaterialApi
@Composable
fun ProfileEditorScreen(navController: NavController, viewModel: ProfileEditorViewModel) {

    when (val state = viewModel.viewState.value) {
        ProfileEditorViewState.Idle -> {
        }
        is ProfileEditorViewState.Editor -> ProfileEditorViewDisplay(
            profile = state.profile,
            onSaveProfile = {
                viewModel.obtainEvent(
                    event = ProfileEditorEvent.OnSaveButtonClicked(it)
                )
            }
        )
        ProfileEditorViewState.EmptyProfile -> ProfileEditorViewDisplay(
            profile = null, onSaveProfile = {
                viewModel.obtainEvent(
                    event = ProfileEditorEvent.OnSaveButtonClicked(it)
                )
            }
        )
        is ProfileEditorViewState.Error -> TODO()
        ProfileEditorViewState.SaveInProgress -> {
        }
    }

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            when (action) {
                ProfileEditorAction.NavigateToDialogList -> {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.EmptyProfileEditor.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}