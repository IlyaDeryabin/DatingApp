package ru.d3rvich.datingapp.ui.screens.login_screen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.LoginEntity
import ru.d3rvich.datingapp.ui.screens.login_screen.models.LoginAction

@Composable
fun LoginViewDisplay(action: LoginAction, onLoginButtonClick: (LoginEntity) -> Unit) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(indication = null, interactionSource = MutableInteractionSource()) { },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text = "Phone number")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = {Text(text = "Password")}
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            val loginEntity = LoginEntity(phoneNumber, password)
            onLoginButtonClick(loginEntity)
        }, enabled = phoneNumber.isNotBlank() && password.isNotBlank()) {
            Text("Log in")
        }
    }
}