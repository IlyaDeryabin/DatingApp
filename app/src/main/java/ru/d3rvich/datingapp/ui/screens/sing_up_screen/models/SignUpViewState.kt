package ru.d3rvich.datingapp.ui.screens.sing_up_screen.models

sealed class SignUpViewState {
    object SignUpDisplay : SignUpViewState()
    object InProgress : SignUpViewState()
    class Error(val errorMessageStringRes: Int) : SignUpViewState()
}
