package ru.d3rvich.datingapp.ui.screens.sing_up_screen.models

import ru.d3rvich.datingapp.ui.model.SignupUiModel

sealed class SignUpEvent {
    object EnterScreen: SignUpEvent()
    object LoginButtonClicked : SignUpEvent()
    class PerformSignUp(val signUpUiModel: SignupUiModel) : SignUpEvent()
}