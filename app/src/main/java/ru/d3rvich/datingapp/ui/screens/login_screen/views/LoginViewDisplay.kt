package ru.d3rvich.datingapp.ui.screens.login_screen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.LoginEntity
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginViewState

@Composable
fun LoginViewDisplay(state: LoginViewState, onLoginButtonClick: (LoginEntity) -> Unit) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val isTextFieldsEnable = state is LoginViewState.Login
    Column(
        modifier = Modifier.fillMaxSize(),
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
                val loginEntity = LoginEntity(phoneNumber, password)
                onLoginButtonClick(loginEntity)
            }, enabled = phoneNumber.isNotBlank() && password.isNotBlank()) {
                Text("Log in")
            }
        } else {
            LinearProgressIndicator()
        }
    }
}