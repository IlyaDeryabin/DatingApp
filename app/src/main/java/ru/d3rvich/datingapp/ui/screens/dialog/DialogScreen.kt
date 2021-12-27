package ru.d3rvich.datingapp.ui.screens.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogAction
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogEvent
import ru.d3rvich.datingapp.ui.screens.dialog.models.DialogViewState
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewDisplay
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewError
import ru.d3rvich.datingapp.ui.screens.dialog.views.DialogViewLoading
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DialogScreen(navController: NavController, viewModel: DialogViewModel = hiltViewModel()) {
    val onSendMessage: (MessageEntity) -> Unit = { message ->
        viewModel.obtainEvent(DialogEvent.SendMessage(message = message))
    }
    val onBackPressed: () -> Unit = {
        viewModel.obtainEvent(DialogEvent.BackPressed)
    }
    when (val state = viewModel.viewState.value) {
        DialogViewState.Loading -> {
            DialogViewLoading(onBackPressed = onBackPressed)
        }
        is DialogViewState.Dialog -> {
            DialogViewDisplay(
                companion = state.dialog.companion,
                messages = state.dialog.messages,
                onSendMessage = { newMessage ->
                    val message = MessageEntity(
                        isMine = true,
                        text = newMessage,
                        dispatchTime = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("hh:mm", Locale.getDefault()))
                    )
                    onSendMessage(message)
                },
                onBackPressed = onBackPressed,
                onCompanionClicked = {
                    viewModel.obtainEvent(DialogEvent.CompanionClicked)
                }
            )
        }
        is DialogViewState.Error -> DialogViewError(
            onBackClicked = onBackPressed,
            onReloadButtonClicked = {
                viewModel.obtainEvent(DialogEvent.ReloadData)
            })
    }

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            when (action) {
                DialogAction.NavigateToCompanion -> {
                    navController.navigate(Screen.ProfileScreen.route)
                }
                DialogAction.PopBackStack -> {
                    navController.popBackStack()
                }
            }
        }
    }
}