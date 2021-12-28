package ru.d3rvich.datingapp.ui.screens.login_screen.models

import ru.d3rvich.datingapp.domain.entity.AuthEntity

sealed class LoginEvent {
    object SignupButtonClicked : LoginEvent()
    class PerformLogin(val authEntity: AuthEntity) : LoginEvent()
}
