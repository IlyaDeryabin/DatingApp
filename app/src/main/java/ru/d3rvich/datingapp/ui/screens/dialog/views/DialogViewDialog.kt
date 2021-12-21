package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick

@Composable
fun DialogViewDialog(
    companion: String,
    messages: List<MessageEntity>,
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
        Column(modifier = Modifier.fillMaxSize()) {
            if (messages.isEmpty()) {
                NoMessages(modifier = Modifier.weight(1f))
            } else {
                Messages(messages = messages, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                var text by rememberSaveable {
                    mutableStateOf("")
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
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

@Composable
private fun NoMessages(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = "Похоже, сообщений нет. Это надо исправить!",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Messages(modifier: Modifier = Modifier, messages: List<MessageEntity>) {
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        state = lazyListState
    ) {
        items(messages) { message ->
            MessageView(message = message.massage, isMine = true)
        }
        scope.launch {
            lazyListState.scrollToItem(messages.lastIndex)
        }
    }
}