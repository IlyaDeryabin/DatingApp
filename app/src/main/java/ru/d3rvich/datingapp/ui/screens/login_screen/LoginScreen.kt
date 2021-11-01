package ru.d3rvich.datingapp.ui.screens.login_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginEvent
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState
import ru.d3rvich.datingapp.ui.screens.login_screen.views.LoginViewDisplay

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (loginViewModel.loginViewState.value) {
            is LoginViewState.Login -> {
                LoginViewDisplay(
                    action = loginViewModel.loginAction.value,
                    onLoginButtonClick = { loginEntity ->
                        loginViewModel.obtainEvent(
                            LoginEvent.PerformLogin(loginEntity)
                        )
                    })
            }
        }
    }
}