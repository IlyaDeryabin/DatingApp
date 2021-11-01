package ru.d3rvich.datingapp.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginAction
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginEvent
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val interactor: DatingInteractor) : ViewModel(),
    EventHandler<LoginEvent> {

    private val _loginViewState = mutableStateOf<LoginViewState>(LoginViewState.Login)
    val loginViewState: State<LoginViewState>
        get() = _loginViewState

    private val _loginAction = mutableStateOf<LoginAction>(LoginAction.Idle)
    val loginAction: State<LoginAction>
        get() = _loginAction

    override fun obtainEvent(event: LoginEvent) {
        when(val currentState = _loginViewState.value) {
            is LoginViewState.Login -> {
                reduce(event = event, state = currentState)
            }
            is LoginViewState.LoginOnProcess -> {
                reduce(event = event, state = currentState)
            }
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.Login) {
        when (event) {
            is LoginEvent.PerformLogin -> {
                _loginViewState.value = LoginViewState.LoginOnProcess
            }
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.LoginOnProcess) {
        error("Invalid $event for $state")
    }
}