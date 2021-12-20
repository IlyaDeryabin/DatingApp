package ru.d3rvich.datingapp.ui.screens.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogViewState

@Composable
fun DialogScreen(navController: NavController, viewModel: DialogViewModel = hiltViewModel()) {
    when (val state = viewModel.viewState.value) {
        DialogViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        DialogViewState.NoMessages -> TODO()
        is DialogViewState.Dialog -> {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(title = { Text(text = "Собеседник") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Arrow back"
                                )
                            }
                        })
                }) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "DialogID: ${state.dialog.dialogId}")
                }
            }
        }
        is DialogViewState.Error -> TODO()
    }
}