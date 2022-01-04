package ru.d3rvich.datingapp.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.domain.utils.AuthResult
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginAction
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginEvent
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val interactor: DatingInteractor) : ViewModel(),
    EventHandler<LoginEvent> {

    private val _loginViewState = mutableStateOf<LoginViewState>(LoginViewState.Display)
    val loginViewState: State<LoginViewState>
        get() = _loginViewState

    private val _loginAction = MutableSharedFlow<LoginAction>()
    val loginAction = _loginAction.asSharedFlow()

    override fun obtainEvent(event: LoginEvent) {
        when (val currentState = _loginViewState.value) {
            is LoginViewState.Display -> {
                reduce(event = event, state = currentState)
            }
            is LoginViewState.LoginOnProcess -> {
                reduce(event = event, state = currentState)
            }
            is LoginViewState.LoginFailure -> {
                reduce(event = event, state = currentState)
            }
        }
    }

    private fun reduce(
        event: LoginEvent,
        @Suppress("UNUSED_PARAMETER") state: LoginViewState.Display
    ) {
        when (event) {
            LoginEvent.EnterScreen -> return
            is LoginEvent.PerformLogin -> {
                performLogin(event.authEntity)
            }
            LoginEvent.SignupButtonClicked -> {
                viewModelScope.launch {
                    _loginAction.emit(LoginAction.NavigateToSignupScreen)
                }
            }
        }
    }

    private fun reduce(
        event: LoginEvent,
        @Suppress("UNUSED_PARAMETER") state: LoginViewState.LoginFailure
    ) {
        when (event) {
            LoginEvent.EnterScreen -> {
                _loginViewState.value = LoginViewState.Display
            }
            is LoginEvent.PerformLogin -> {
                viewModelScope.launch {
                    performLogin(event.authEntity)
                }
            }
            LoginEvent.SignupButtonClicked -> {
                viewModelScope.launch {
                    _loginAction.emit(LoginAction.NavigateToSignupScreen)
                }
            }
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.LoginOnProcess) {
        when (event) {
            LoginEvent.EnterScreen -> return
            is LoginEvent.PerformLogin -> error("Unexpected $event for $state")
            LoginEvent.SignupButtonClicked -> {
                viewModelScope.launch {
                    _loginAction.emit(LoginAction.NavigateToSignupScreen)
                }
            }
        }
    }

    private fun performLogin(authEntity: AuthEntity) {
        viewModelScope.launch {
            _loginViewState.value = LoginViewState.LoginOnProcess
            when (val result = interactor.performLogin(authEntity)) {
                is AuthResult.Error -> {
                    _loginViewState.value = LoginViewState.LoginFailure(result.exception)
                }
                AuthResult.Success -> _loginAction.emit(LoginAction.NavigateToMainScreen)
            }
        }
    }
}