package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.domain.entity.UserEntity
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick

@Composable
fun DialogViewDisplay(
    companion: UserEntity,
    messages: List<MessageEntity>,
    onSendMessage: (String) -> Unit,
    onBackPressed: () -> Unit,
    onCompanionClicked: () -> Unit
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .clearFocusOnClick(),
        topBar = {
            TopBar(
                companion = companion,
                onCompanionClick = onCompanionClicked,
                onBackPressed = onBackPressed
            )
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (messages.isEmpty()) {
                NoMessages(modifier = Modifier.weight(1f))
            } else {
                Messages(messages = messages, modifier = Modifier.weight(1f))
            }
            InputField(onSendMessage = onSendMessage)
        }
    }
}

@Composable
private fun TopBar(
    companion: UserEntity,
    onCompanionClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onCompanionClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imagePainter = rememberImagePainter(
                    data = companion.photoLink,
                    builder = {
                        crossfade(true)
                    })
                Image(
                    painter = imagePainter,
                    contentDescription = companion.name,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = companion.name)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        },
        actions = {
            var menuVisible by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = { menuVisible = !menuVisible }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Show menu"
                )
            }
            DropdownMenu(
                expanded = menuVisible,
                onDismissRequest = { menuVisible = false }
            ) {
                DropdownMenuItem(onClick = { }) {
                    val text = stringResource(id = R.string.delete)
                    Icon(imageVector = Icons.Default.Delete, contentDescription = text)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = text)
                }
                DropdownMenuItem(onClick = { }) {
                    val text = stringResource(id = R.string.block_user)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_block_24),
                        contentDescription = text
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = text)
                }
            }
        }
    )
}

@Composable
private fun InputField(onSendMessage: (String) -> Unit) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {
                onSendMessage(text)
                text = ""
            }, enabled = text.isNotBlank()) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.send_message)
                )
            }
        },
        maxLines = 5,
        placeholder = {
            Text(text = stringResource(id = R.string.message))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Send,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(onSend = {
            onSendMessage(text)
            text = ""
        }),
        colors = TextFieldDefaults.textFieldColors(trailingIconColor = MaterialTheme.colors.primary)
    )
}

@Composable
private fun NoMessages(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.no_messages),
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
            MessageView(message = message)
        }
        scope.launch {
            lazyListState.scrollToItem(messages.lastIndex)
        }
    }
}

@Preview(showBackground = true, name = "No messages")
@Composable
fun DialogViewDialogPreviewNoMessages() {
    DialogViewDisplay(
        UserEntity("", "Кетя", "Всё равно не заработает"),
        emptyList(),
        {},
        {},
        {}
    )
}

@Preview(showBackground = true, name = "Default")
@Composable
fun DialogViewDialogPreview() {
    val messages = listOf(
        MessageEntity(true, "Привет", "1ч"),
        MessageEntity(false, "Нет", "30мин"),
        MessageEntity(true, "Ладно(", "2мин")
    )
    DialogViewDisplay(
        UserEntity("", "Кетя", "Всё равно не заработает"),
        messages,
        {},
        {},
        {}
    )
}

