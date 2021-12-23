package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R

@Composable
fun DialogViewError(onBackClicked: () -> Unit, onReloadButtonClicked: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(text = ":(")
        }, navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        })
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.loading_error_message))
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onReloadButtonClicked) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

@Preview
@Composable
fun DialogViewErrorPreview() {
    DialogViewError({}, {})
}