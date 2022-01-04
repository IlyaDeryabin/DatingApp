package ru.d3rvich.datingapp.ui.common

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.d3rvich.datingapp.R

@Composable
fun PhoneNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.clearFocusOnKeyboardDismiss(),
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.phone_number)) },
        enabled = enabled,
        isError = isError,
        leadingIcon = { Text("+7") },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}