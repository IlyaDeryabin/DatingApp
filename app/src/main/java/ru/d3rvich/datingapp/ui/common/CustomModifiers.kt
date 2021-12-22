package ru.d3rvich.datingapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager

fun Modifier.clearFocusOnClick(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    return@composed this.clickable(MutableInteractionSource(), indication = null) {
        focusManager.clearFocus()
    }
}