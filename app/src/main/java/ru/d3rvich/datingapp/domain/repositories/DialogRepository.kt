package ru.d3rvich.datingapp.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity

interface DialogRepository {
    suspend fun getDialogList(): List<DialogListItemEntity>

    suspend fun getDialogBy(id: String): DialogEntity

    suspend fun sendMessage(dialogId: String, messageEntity: MessageEntity) : Result<Unit>

    suspend fun getDialogFlow(dialogId: String): Flow<MessageEntity>
}