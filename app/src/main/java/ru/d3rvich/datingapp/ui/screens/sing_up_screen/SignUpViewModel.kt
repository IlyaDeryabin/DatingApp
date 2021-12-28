package ru.d3rvich.datingapp.ui.screens.sing_up_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.domain.utils.AuthResult
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.mappers.toAuthEntity
import ru.d3rvich.datingapp.ui.model.SingUpUiModel
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpAction
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpEvent
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpViewState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val interactor: DatingInteractor) : ViewModel(),
    EventHandler<SignUpEvent> {

    private val _signUpViewState = mutableStateOf<SignUpViewState>(SignUpViewState.SignUpDisplay)
    val signUpViewState: State<SignUpViewState>
        get() = _signUpViewState

    private val _signUpAction = MutableSharedFlow<SignUpAction>()
    val signUpAction = _signUpAction.asSharedFlow()

    override fun obtainEvent(event: SignUpEvent) {
        when (val viewState = _signUpViewState.value) {
            is SignUpViewState.SignUpDisplay -> {
                reduce(event, viewState)
            }
            is SignUpViewState.InProgress -> {
                reduce(event, viewState)
            }
            is SignUpViewState.Error -> {
                reduce(event, viewState)
            }

        }
    }

    private fun reduce(
        event: SignUpEvent,
        @Suppress("UNUSED_PARAMETER") viewState: SignUpViewState.SignUpDisplay
    ) {
        when (event) {
            is SignUpEvent.EnterScreen -> return
            is SignUpEvent.PerformSignUp -> {
                viewModelScope.launch {
                    performSignup(event.signUpUiModel)
                }
            }
            is SignUpEvent.LoginButtonClicked -> {
                viewModelScope.launch {
                    _signUpAction.emit(SignUpAction.NavigateToLoginScreen)
                }
            }
        }
    }

    private fun reduce(event: SignUpEvent, viewState: SignUpViewState.InProgress) {
        throw NotImplementedError("Unexpected $event for $viewState.")
    }

    private fun reduce(
        event: SignUpEvent,
        @Suppress("UNUSED_PARAMETER") viewState: SignUpViewState.Error
    ) {
        when (event) {
            is SignUpEvent.EnterScreen -> {
                _signUpViewState.value = SignUpViewState.SignUpDisplay
            }
            is SignUpEvent.LoginButtonClicked -> {
                viewModelScope.launch {
                    _signUpAction.emit(SignUpAction.NavigateToLoginScreen)
                }
            }
            is SignUpEvent.PerformSignUp -> {
                viewModelScope.launch {
                    performSignup(event.signUpUiModel)
                }
            }
        }
    }

    private suspend fun performSignup(signUpUiModel: SingUpUiModel) {
        if (signUpUiModel.passwordFirst == signUpUiModel.passwordSecond) {
            _signUpViewState.value = SignUpViewState.InProgress
            val authEntity = signUpUiModel.toAuthEntity()
            when (interactor.performSignUp(authEntity)) {
                is AuthResult.Error ->
                    _signUpViewState.value = SignUpViewState.Error("Ошибка при регистрации")
                AuthResult.Success -> _signUpAction.emit(SignUpAction.SignUpSuccessful)
            }
        } else {
            _signUpViewState.value = SignUpViewState.Error("Пароли не совпадают")
        }
    }
}