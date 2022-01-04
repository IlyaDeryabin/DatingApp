package ru.d3rvich.datingapp.ui.screens.login_screen.models

import ru.d3rvich.datingapp.domain.exceptions.AuthException

sealed class LoginViewState {
    object Display : LoginViewState()
    object LoginOnProcess : LoginViewState()
    class LoginFailure(val exception: AuthException) : LoginViewState()
}
