package ru.d3rvich.datingapp.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.ui.DrawerScreen
import ru.d3rvich.datingapp.ui.Screen

@Composable
fun Drawer(
    profile: ProfileEntity,
    onDestinationClicked: (destination: String) -> Unit,
    onUserProfileClicked: () -> Unit
) {
    val screens = listOf(
        DrawerScreen.DialogListScreen,
        DrawerScreen.PairSearchScreen
    )
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileView(profileEntity = profile, onUserProfileClicked = onUserProfileClicked)
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
        "https://static.probusiness.io/n/03/d/38097027_439276526579800_2735888197547458560_n.jpg"
    )
    Drawer(onDestinationClicked = {}, profile = profile) {
    }
}