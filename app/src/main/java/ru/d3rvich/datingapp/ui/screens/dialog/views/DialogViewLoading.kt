package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun DialogViewLoading(onBackPressed: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Content to display",
                    modifier = Modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = MaterialTheme.colors.primary
                    )
                )
            }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            })
        }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}