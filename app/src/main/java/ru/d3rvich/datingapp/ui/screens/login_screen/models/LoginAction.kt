package ru.d3rvich.datingapp.ui.screens.login_screen.models

sealed class LoginAction {
    object Idle: LoginAction()
    object LoginSuccessful : LoginAction()
    class Failure(val message: String) : LoginAction()
}
