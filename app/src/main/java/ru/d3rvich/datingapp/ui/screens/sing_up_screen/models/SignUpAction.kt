package ru.d3rvich.datingapp.ui.screens.sing_up_screen.models

sealed class SignUpAction {
    object SignUpSuccessful: SignUpAction()
    object NavigateToLoginScreen: SignUpAction()
}
