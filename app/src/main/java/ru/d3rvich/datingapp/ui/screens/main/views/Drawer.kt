package ru.d3rvich.datingapp.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.ui.DrawerScreen
import ru.d3rvich.datingapp.ui.Screen

@Composable
fun Drawer(
    profile: ProfileEntity,
    onDestinationClicked: (destination: String) -> Unit,
    onUserProfileClicked: () -> Unit,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    val screens = listOf(
        DrawerScreen.DialogListScreen,
        DrawerScreen.PairSearchScreen
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ProfileView(
                profileEntity = profile,
                onUserProfileClicked = onUserProfileClicked,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { onDarkModeChange(!isDarkMode) }) {
                if (isDarkMode) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_light_mode_24),
                        contentDescription = stringResource(id = R.string.turn_on_light_mode)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_dark_mode_24),
                        contentDescription = stringResource(id = R.string.turn_on_dark_mode)
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {
            screens.forEach { screen ->
                TextButton(onClick = { onDestinationClicked(screen.route) }) {
                    Text(text = stringResource(id = screen.titleResId))
                }
            }
            TextButton(onClick = { onDestinationClicked(Screen.Settings.route) }) {
                Text(text = stringResource(id = Screen.Settings.titleResId!!))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val profile = ProfileEntity(
        "Имя",
        "Ёбург",
        0,
        DateEntity(0, 0, 0),
        "Описание",
        0,
        0,
        0,
        0,
        ""
    )
    Drawer(
        onDestinationClicked = {},
        profile = profile,
        isDarkMode = false,
        onDarkModeChange = {},
        onUserProfileClicked = {})
}