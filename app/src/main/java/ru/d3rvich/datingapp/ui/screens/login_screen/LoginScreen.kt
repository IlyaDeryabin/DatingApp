package ru.d3rvich.datingapp.ui.screens.login_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginAction
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginEvent
import ru.d3rvich.datingapp.ui.screens.login_screen.views.LoginViewDisplay

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    LaunchedEffect(Unit) {
        loginViewModel.loginAction.collect { action ->
            when (action) {
                is LoginAction.NavigateToMainScreen -> {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
                LoginAction.NavigateToSignupScreen -> {
                    navController.navigate(Screen.SignUpScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LoginViewDisplay(
            state = loginViewModel.loginViewState.value,
            onLoginButtonClick = { loginEntity ->
                loginViewModel.obtainEvent(
                    LoginEvent.PerformLogin(loginEntity)
                )
            }, onSignUpClicked = {
                loginViewModel.obtainEvent(
                    LoginEvent.SignupButtonClicked
                )
            })
    }
}