package ru.d3rvich.datingapp.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.d3rvich.datingapp.R

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes labelStringRes: Int = R.string.password,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val visibilityIcon: @Composable () -> Unit = {
        if (isPasswordVisible) {
            Icon(painter = painterResource(id = R.drawable.ic_visibility_off_24),
                contentDescription = "")
        } else {
            Icon(painter = painterResource(id = R.drawable.ic_visibility_24),
                contentDescription = "")
        }
    }
    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.clearFocusOnKeyboardDismiss(),
        singleLine = true,
        visualTransformation = visualTransformation,
        label = { Text(text = stringResource(id = labelStringRes)) },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible },
                content = visibilityIcon)
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}