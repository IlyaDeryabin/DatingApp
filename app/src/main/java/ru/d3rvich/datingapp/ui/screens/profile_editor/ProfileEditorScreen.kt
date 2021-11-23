package ru.d3rvich.datingapp.ui.screens.profile_editor

import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screens
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorAction
import ru.d3rvich.datingapp.ui.screens.profile_editor.models.ProfileEditorViewState
import ru.d3rvich.datingapp.ui.screens.profile_editor.views.ProfileEditorViewDisplay
import ru.d3rvich.datingapp.ui.screens.profile_editor.views.ProfileEditorViewEmptyProfile

@Composable
fun ProfileEditorScreen(navController: NavController, viewModel: ProfileEditorViewModel) {

    var isError by remember {
        mutableStateOf(false)
    }

    when (val state = viewModel.viewState.value) {
        ProfileEditorViewState.Idle -> {
        }
        is ProfileEditorViewState.Editor -> ProfileEditorViewDisplay(
            profile = state.profile,
            isError = isError
        )
        ProfileEditorViewState.EmptyProfile -> ProfileEditorViewEmptyProfile(isError = isError)
        is ProfileEditorViewState.Error -> TODO()
        ProfileEditorViewState.SaveInProgress -> TODO()
    }

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            when (action) {
                ProfileEditorAction.NavigateToDialogList -> {
                    navController.navigate(Screens.DialogListScreen.route)
                }
            }
        }
    }
}