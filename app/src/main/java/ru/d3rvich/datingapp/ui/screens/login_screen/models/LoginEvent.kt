package ru.d3rvich.datingapp.ui.screens.login_screen.models

import ru.d3rvich.datingapp.domain.entity.LoginEntity

sealed class LoginEvent {
    class PerformLogin(val loginEntity: LoginEntity) : LoginEvent()
}
