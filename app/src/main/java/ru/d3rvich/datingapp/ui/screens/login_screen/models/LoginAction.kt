package ru.d3rvich.datingapp.ui.screens.login_screen.models

sealed class LoginAction {
    object NavigateToMainScreen : LoginAction()
    object NavigateToSignupScreen : LoginAction()
}
