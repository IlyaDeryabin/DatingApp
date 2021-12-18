package ru.d3rvich.datingapp.ui.screens.pair_search

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun PairSearchScreen(openDrawer: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Arrow back"
                )
            }
        }, title = {})
    }) {
        Text(text = "Скоро тут будет весело!")
    }
}