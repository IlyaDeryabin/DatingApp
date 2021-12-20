package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MessageView(modifier: Modifier = Modifier, message: String, isMine: Boolean) {
    val align: Alignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart

    Box(modifier = modifier.fillMaxWidth()) {
        Text(text = message, modifier = Modifier.align(align))
    }
}