package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick

@Composable
fun DialogViewNoMessages(
    companion: String,
    onSendMessage: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .clearFocusOnClick(),
        topBar = {
            TopAppBar(title = { Text(text = companion) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                })
        }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Похоже, сообщений нет. Это надо исправить!",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                var text by rememberSaveable {
                    mutableStateOf("")
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    onSendMessage(text)
                    text = ""
                }, enabled = text.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.send_message)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogViewNoMessagesPreview() {
    DialogViewNoMessages("Кетя", {}, {})
}