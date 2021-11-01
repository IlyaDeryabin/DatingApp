package ru.d3rvich.datingapp.ui.screens.login_screen.models

sealed class LoginViewState {
    object Login : LoginViewState()
    object LoginOnProcess: LoginViewState()
}
