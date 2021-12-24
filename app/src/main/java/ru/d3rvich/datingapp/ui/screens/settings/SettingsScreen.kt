package ru.d3rvich.datingapp.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.Screen

@Composable
fun SettingsScreen(
    onBackButtonPressed: () -> Unit,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onBackButtonPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.turn_back)
                )
            }
        }, title = {
            Text(text = stringResource(id = Screen.Settings.titleResId!!))
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.enable_dark_theme),
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    style = MaterialTheme.typography.body1
                )
                Checkbox(
                    checked = isDarkMode,
                    onCheckedChange = { onDarkModeChange(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBackButtonPressed = {}, isDarkMode = true, onDarkModeChange = {})
}