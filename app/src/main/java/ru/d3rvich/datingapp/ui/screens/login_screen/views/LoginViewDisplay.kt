package ru.d3rvich.datingapp.ui.screens.login_screen.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.ui.common.PasswordField
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun LoginViewDisplay(
    state: LoginViewState,
    onLoginButtonClick: (AuthEntity) -> Unit,
    onSignUpClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val phoneFieldFocusRequester = remember { FocusRequester() }
    val passwordFieldFocusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    val isPhoneNumberValid: Boolean = phoneNumber.isNotBlank() && phoneNumber.length == 10
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val isTextFieldsEnable = state is LoginViewState.Login

    Box(modifier = Modifier
        .fillMaxHeight(0.8f)
        .fillMaxWidth()
        .clickable(
            indication = null,
            interactionSource = interactionSource
        ) { focusManager.clearFocus() }) {
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
            OutlinedTextField(
                singleLine = true,
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it.replace(Regex("[^0-9]"), "")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = when {
                        phoneNumber.isBlank() -> ImeAction.None
                        password.isNotBlank() -> ImeAction.Done
                        else -> ImeAction.Next
                    }
                ),
                label = { Text(text = stringResource(id = R.string.phone_number)) },
                enabled = isTextFieldsEnable,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(phoneFieldFocusRequester)
                    .onKeyEvent {
                        if (it.key == Key.Back) {
                            focusManager.clearFocus()
                            true
                        } else {
                            false
                        }
                    },
                keyboardActions = KeyboardActions(onNext = {
                    passwordFieldFocusRequester.requestFocus()
                }, onDone = {
                    focusManager.clearFocus()
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                }),
                leadingIcon = { Text("+7") },
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
                        phoneNumber.isNotBlank() -> ImeAction.Done
                        else -> ImeAction.Next
                    }, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                }, onNext = { phoneFieldFocusRequester.requestFocus() }),
                modifier = Modifier
                    .focusRequester(passwordFieldFocusRequester)
                    .onKeyEvent {
                        if (it.key == Key.Back) {
                            focusManager.clearFocus()
                            true
                        } else {
                            false
                        }
                    }
                    .fillMaxWidth()
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
                    keyboardController?.hide()
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