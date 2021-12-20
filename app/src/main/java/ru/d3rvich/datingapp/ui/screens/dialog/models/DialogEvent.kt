package ru.d3rvich.datingapp.ui.screens.dialog.models

import ru.d3rvich.datingapp.domain.entity.MessageEntity

sealed class DialogEvent {
    class SendMessage(val message: MessageEntity): DialogEvent()
    object ReloadData: DialogEvent()
}
