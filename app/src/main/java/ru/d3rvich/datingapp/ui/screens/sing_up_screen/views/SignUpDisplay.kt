package ru.d3rvich.datingapp.ui.screens.sing_up_screen.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.common.PasswordField
import ru.d3rvich.datingapp.ui.common.PhoneNumberField
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick
import ru.d3rvich.datingapp.ui.model.SingUpUiModel
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpViewState

@ExperimentalAnimationApi
@Composable
fun SignUpDisplay(
    viewState: SignUpViewState,
    onSignUpButtonClicked: (SingUpUiModel) -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnClick()
    ) {
        Text(text = stringResource(id = R.string.log_in),
            modifier = Modifier
                .padding(8.dp)
                .clickable { if (viewState !is SignUpViewState.InProgress) onLoginButtonClicked() }
                .align(Alignment.TopEnd),
            fontStyle = FontStyle.Italic
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var phoneNumber by rememberSaveable {
                mutableStateOf("")
            }
            var passwordFirst by rememberSaveable {
                mutableStateOf("")
            }
            var passwordSecond by rememberSaveable {
                mutableStateOf("")
            }
            val isPhoneNumberValid: Boolean = phoneNumber.isNotBlank() && phoneNumber.length == 10
            PhoneNumberField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it.replace(Regex("[^0-9]"), "") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                isError = phoneNumber.isNotBlank() && !isPhoneNumberValid
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = passwordFirst,
                onValueChange = { passwordFirst = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = passwordSecond,
                onValueChange = { passwordSecond = it },
                labelStringRes = R.string.repeat_password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            AnimatedVisibility(visible = viewState is SignUpViewState.Error) {
                if (viewState is SignUpViewState.Error) {
                    Text(
                        text = stringResource(id = viewState.errorMessageStringRes),
                        modifier = Modifier.padding(top = 16.dp),
                        color = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (viewState !is SignUpViewState.InProgress) {
                        val signUpUiModel =
                            SingUpUiModel(phoneNumber, passwordFirst, passwordSecond)
                        onSignUpButtonClicked(signUpUiModel)
                    }
                },
                modifier = Modifier.animateContentSize(),
                enabled = phoneNumber.isNotEmpty() &&
                        passwordFirst.isNotEmpty() &&
                        passwordSecond.isNotEmpty() &&
                        isPhoneNumberValid
            ) {
                if (viewState is SignUpViewState.InProgress) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Text(text = stringResource(id = R.string.sign_up))
                }
            }
        }
    }
}