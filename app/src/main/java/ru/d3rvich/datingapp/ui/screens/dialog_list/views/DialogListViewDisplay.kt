package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity

@ExperimentalCoilApi
@Composable
fun DialogListViewDisplay(
    modifier: Modifier = Modifier,
    dialogs: List<DialogListItemEntity>,
    onItemClicked: (String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(dialogs) { dialog: DialogListItemEntity ->
            DialogListItem(dialogListItemEntity = dialog, onItemClicked = onItemClicked)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DialogListViewDisplayPreview() {
    val messageEntity = MessageEntity("0", "Привет", "")
    val dialogs = listOf(
        DialogListItemEntity("0", "Роман", "", messageEntity),
        DialogListItemEntity("1", "Олег", "", messageEntity),
        DialogListItemEntity("2", "Олег", "", messageEntity),
        DialogListItemEntity("3", "Олег", "", messageEntity)
    )
    DialogListViewDisplay(dialogs = dialogs, onItemClicked = {})
}