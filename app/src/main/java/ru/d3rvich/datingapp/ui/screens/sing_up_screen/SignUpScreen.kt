package ru.d3rvich.datingapp.ui.screens.sing_up_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpAction
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpEvent
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.views.SignUpDisplay

@ExperimentalAnimationApi
@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel) {
    LaunchedEffect(Unit) {
        signUpViewModel.obtainEvent(SignUpEvent.EnterScreen)
        signUpViewModel.signUpAction.collect { action ->
            when (action) {
                is SignUpAction.SignUpSuccessful -> {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.SignUpScreen.route) {
                            inclusive = true
                        }
                    }
                }
                is SignUpAction.NavigateToLoginScreen -> {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.SignUpScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SignUpDisplay(
            viewState = signUpViewModel.signUpViewState.value,
            onSignUpButtonClicked = { authEntity ->
                signUpViewModel.obtainEvent(SignUpEvent.PerformSignUp(authEntity))
            },
            onLoginButtonClicked = {
                signUpViewModel.obtainEvent(SignUpEvent.LoginButtonClicked)
            })
    }
}