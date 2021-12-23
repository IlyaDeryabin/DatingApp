package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import coil.annotation.ExperimentalCoilApi
import ru.d3rvich.datingapp.R
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
    val focusManager = LocalFocusManager.current
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
                    placeholder = { Text(text = stringResource(id = R.string.search_placeholder_text)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        IconButton(onClick = {
                            focusManager.clearFocus()
                            onBackPressed()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.turn_back)
                            )
                        }
                    },
                    trailingIcon = {
                        AnimatedVisibility(visible = text.isNotEmpty()) {
                            IconButton(onClick = { onTextChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = stringResource(id = R.string.clear)
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
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.h6
                )
            }
        } else {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(dialogs) { dialog: DialogListItemEntity ->
                    DialogListItem(
                        dialogListItemEntity = dialog,
                        onItemClicked = {
                            focusManager.clearFocus()
                            onItemClicked(it)
                        }
                    )
                }
            }
        }
    }
}