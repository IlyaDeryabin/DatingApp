package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R

@Composable
fun DialogListViewError(onReloadButtonClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.dialogs_error_message),
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(onClick = onReloadButtonClicked) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogListViewErrorPreview() {
    DialogListViewError(onReloadButtonClicked = {})
}