package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import coil.annotation.ExperimentalCoilApi
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick

@ExperimentalCoilApi
@Composable
fun DialogListViewSearch(
    text: String,
    onTextChange: (String) -> Unit,
    dialogs: List<DialogListItemEntity>,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier
        .fillMaxSize()
        .clearFocusOnClick(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = text,
                    onValueChange = onTextChange,
                    placeholder = { Text(text = "Кого ищем?") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    trailingIcon = {
                        AnimatedVisibility(visible = text.isNotEmpty()) {
                            IconButton(onClick = { onTextChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    )
                )
            }
        }) {
        if (dialogs.isEmpty()) {
            Text(text = "Пусто")
        } else {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(dialogs) { dialog: DialogListItemEntity ->
                    DialogListItem(dialogListItemEntity = dialog, onItemClicked = onItemClicked)
                }
            }
        }
    }
}