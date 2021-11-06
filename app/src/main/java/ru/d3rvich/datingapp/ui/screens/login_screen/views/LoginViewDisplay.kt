package ru.d3rvich.datingapp.ui.screens.login_screen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState

@Composable
fun LoginViewDisplay(
    state: LoginViewState,
    onLoginButtonClick: (AuthEntity) -> Unit,
    onSignUpClicked: () -> Unit
) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val isTextFieldsEnable = state is LoginViewState.Login
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.sign_up), modifier = Modifier
            .padding(8.dp)
            .clickable {
                onSignUpClicked()
            }
            .align(Alignment.TopEnd), fontStyle = FontStyle.Italic)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text(text = "Phone number") },
                enabled = isTextFieldsEnable
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Password") },
                enabled = isTextFieldsEnable
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (state is LoginViewState.Login) {
                Button(onClick = {
                    val loginEntity = AuthEntity(phoneNumber, password)
                    onLoginButtonClick(loginEntity)
                }, enabled = phoneNumber.isNotBlank() && password.isNotBlank()) {
                    Text("Log in")
                }
            } else {
                LinearProgressIndicator()
            }
        }
    }
}