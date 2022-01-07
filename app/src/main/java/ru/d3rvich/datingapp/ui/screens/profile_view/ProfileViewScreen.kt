package ru.d3rvich.datingapp.ui.screens.profile_view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.profile_view.models.ProfileViewViewState
import ru.d3rvich.datingapp.ui.screens.profile_view.views.ProfileViewDisplay

@Composable
fun ProfileViewScreen(
    navController: NavController,
    viewModel: ProfileViewViewModel = hiltViewModel()
) {
    when (val viewState = viewModel.viewState.value) {
        is ProfileViewViewState.Display -> {
            ProfileViewDisplay(
                profile = viewState.profile,
                onBackPressed = {
                    navController.popBackStack()
                },
                onEditProfileClicked = {
                    navController.navigate(Screen.EmptyProfileEditor.route)
                }
            )
        }
        is ProfileViewViewState.Error -> {
            // TODO: 07.01.2022 Добавить обработку состояния
        }
        ProfileViewViewState.Loading -> {
            // TODO: 07.01.2022 Добавить обработку состояния
        }
    }
}