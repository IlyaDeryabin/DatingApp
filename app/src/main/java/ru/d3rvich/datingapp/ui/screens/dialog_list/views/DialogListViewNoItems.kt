package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.d3rvich.datingapp.R

@Composable
fun DialogListViewNoItems(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.no_dialogs_text),
            style = MaterialTheme.typography.body1)
    }
}

@Preview(showBackground = true)
@Composable
fun DialogListViewNoItemsPreview() {
    DialogListViewNoItems()
}