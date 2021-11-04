package ru.d3rvich.datingapp.ui.screens.sing_up_screen.models

import ru.d3rvich.datingapp.domain.entity.AuthEntity

sealed class SignUpEvent {
    object LoginButtonClicked: SignUpEvent()
    object DismissErrorState: SignUpEvent()
    class PerformSignUp(val authEntity: AuthEntity): SignUpEvent()
}
