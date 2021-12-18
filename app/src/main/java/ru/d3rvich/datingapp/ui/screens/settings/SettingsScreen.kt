package ru.d3rvich.datingapp.ui.screens.settings

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.d3rvich.datingapp.ui.Screen

@Composable
fun SettingsScreen(onBackButtonClicked: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onBackButtonClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        }, title = {
            Text(text = stringResource(id = Screen.Settings.titleResId!!))
        })
    }) {
        Text(text = "Скоро тут будет весело!")
    }
}