package ru.d3rvich.datingapp.ui.mappers

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.ui.model.EmptyDialogUiModel
import ru.d3rvich.datingapp.ui.model.SingUpUiModel

fun SingUpUiModel.toAuthEntity(): AuthEntity {
    if (passwordFirst == passwordSecond) {
        return AuthEntity(phoneNumber, passwordFirst)
    } else {
        error("Пароли не совпадают. $passwordFirst и $passwordSecond должны быть равыны.")
    }
}

fun EmptyDialogUiModel.toDialogEntity(messages: List<MessageEntity>): DialogEntity = DialogEntity(
    dialogId = dialogId,
    companion = companion,
    messages = messages
)

fun DialogEntity.toEmptyDialogUiModel(): EmptyDialogUiModel {
    require(messages.isEmpty()) {
        "Вызвав этот метод, ты бы потерял данные! Проверь свой код."
    }
    return EmptyDialogUiModel(dialogId = dialogId, companion = companion)
}