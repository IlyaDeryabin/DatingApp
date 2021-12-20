package ru.d3rvich.datingapp.ui.screens.profile_editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorAction
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorEvent
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorViewState
import ru.d3rvich.datingapp.ui.screens.profile_editor.views.ProfileEditorViewDisplay
import ru.d3rvich.datingapp.R

@ExperimentalMaterialApi
@Composable
fun ProfileEditorScreen(navController: NavController, viewModel: ProfileEditorViewModel) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.edit_profile)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                })
        }) {
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