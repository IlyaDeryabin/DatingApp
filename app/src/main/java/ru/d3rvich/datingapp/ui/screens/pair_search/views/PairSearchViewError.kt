package ru.d3rvich.datingapp.ui.screens.pair_search.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.R

@Composable
fun PairSearchViewError(onReloadButtonClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.loading_error_message))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onReloadButtonClicked) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}