package ru.d3rvich.datingapp.ui.screens.dialog

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogEvent
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogViewState
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewDialog
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewError
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewLoading

@Composable
fun DialogScreen(navController: NavController, viewModel: DialogViewModel = hiltViewModel()) {
    val onSendMessage: (MessageEntity) -> Unit = { message ->
        viewModel.obtainEvent(DialogEvent.SendMessage(message = message))
    }
    val onBackPressed: () -> Unit = {
        navController.popBackStack()
    }
    when (val state = viewModel.viewState.value) {
        DialogViewState.Loading -> {
            DialogViewLoading(onBackPressed = onBackPressed)
        }
        is DialogViewState.Dialog -> {
            DialogViewDialog(
                companion = state.dialog.companion.name,
                messages = state.dialog.messages,
                onSendMessage = { newMessage ->
                    val message = MessageEntity(
                        isMine = true,
                        text = newMessage,
                        dispatchTime = "now"
                    )
                    onSendMessage(message)
                },
                onBackPressed = onBackPressed
            )
        }
        is DialogViewState.Error -> DialogViewError(
            onBackClicked = onBackPressed,
            onReloadButtonClicked = {
                viewModel.obtainEvent(DialogEvent.ReloadData)
            })
    }
}