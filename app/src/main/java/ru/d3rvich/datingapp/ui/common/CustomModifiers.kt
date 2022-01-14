package ru.d3rvich.datingapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import com.google.accompanist.insets.LocalWindowInsets

fun Modifier.clearFocusOnClick(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    return@composed this.clickable(MutableInteractionSource(), indication = null) {
        focusManager.clearFocus()
    }
}

fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember {
        mutableStateOf(false)
    }
    var keyboardAppearedSinceLastFocused by remember {
        mutableStateOf(false)
    }
    if (isFocused) {
        val imeIsVisible = LocalWindowInsets.current.ime.isVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}

fun Modifier.clearFocusOnMenuDismiss(expanded: Boolean): Modifier = composed {
    var isFocused by remember {
        mutableStateOf(false)
    }
    var menuAppearedSinceLastFocused by remember {
        mutableStateOf(false)
    }
    if (isFocused) {
        val focusManager = LocalFocusManager.current
        LaunchedEffect(expanded) {
            when {
                expanded -> {
                    menuAppearedSinceLastFocused = true
                }
                menuAppearedSinceLastFocused -> {
                    focusManager.clearFocus()
                }
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                menuAppearedSinceLastFocused = false
            }
        }
    }
}