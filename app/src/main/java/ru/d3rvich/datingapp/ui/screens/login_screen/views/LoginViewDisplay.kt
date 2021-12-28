package ru.d3rvich.datingapp.ui.screens.login_screen.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.ui.common.PasswordField
import ru.d3rvich.datingapp.ui.common.PhoneNumberField
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun LoginViewDisplay(
    state: LoginViewState,
    onLoginButtonClick: (AuthEntity) -> Unit,
    onSignUpClicked: () -> Unit,
) {
    val phoneFieldFocusRequester = remember { FocusRequester() }
    val passwordFieldFocusRequester = remember { FocusRequester() }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val isPhoneNumberValid: Boolean = phoneNumber.isNotBlank() && phoneNumber.length == 10
    val isTextFieldsEnable: Boolean = state is LoginViewState.Login

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnClick()
    ) {
        val focusManager = LocalFocusManager.current
        Text(text = stringResource(id = R.string.sign_up), modifier = Modifier
            .padding(8.dp)
            .clickable {
                focusManager.clearFocus()
                onSignUpClicked()
            }
            .align(Alignment.TopEnd), fontStyle = FontStyle.Italic)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhoneNumberField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it.replace(Regex("[^0-9]"), "")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = when {
                        phoneNumber.isBlank() -> ImeAction.None
                        password.isNotBlank() && isPhoneNumberValid -> ImeAction.Done
                        else -> ImeAction.Next
                    }
                ), enabled = isTextFieldsEnable,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(phoneFieldFocusRequester)
                    .onKeyEvent { onBackKeyEvent(it, focusManager) },
                keyboardActions = KeyboardActions(onNext = {
                    passwordFieldFocusRequester.requestFocus()
                }, onDone = {
                    focusManager.clearFocus()
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                }),
                isError = phoneNumber.isNotBlank() && !isPhoneNumberValid
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = password,
                onValueChange = { password = it },
                enabled = isTextFieldsEnable,
                keyboardOptions = KeyboardOptions(
                    imeAction = when {
                        password.isBlank() -> ImeAction.None
                        phoneNumber.isNotBlank() && isPhoneNumberValid -> ImeAction.Done
                        else -> ImeAction.Next
                    }, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                }, onNext = { phoneFieldFocusRequester.requestFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFieldFocusRequester)
                    .onKeyEvent { onBackKeyEvent(it, focusManager) }
            )
            AnimatedVisibility(visible = state is LoginViewState.LoginFailure) {
                Text(
                    text = "Ошибка при входе в систему",
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    focusManager.clearFocus()
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                },
                enabled = isPhoneNumberValid && password.isNotBlank(),
                modifier = Modifier.animateContentSize()
            ) {
                if (state is LoginViewState.LoginOnProcess) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = Color.White, strokeWidth = 2.dp
                    )
                } else {
                    Text(stringResource(id = R.string.log_in))
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
fun onBackKeyEvent(keyEvent: KeyEvent, focusManager: FocusManager): Boolean {
    return if (keyEvent.key == Key.Back) {
        focusManager.clearFocus()
        true
    } else {
        false
    }
}