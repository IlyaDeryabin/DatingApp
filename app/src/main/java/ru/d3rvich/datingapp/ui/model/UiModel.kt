package ru.d3rvich.datingapp.ui.model

import ru.d3rvich.datingapp.domain.entity.UserEntity

data class SingUpUiModel(
    val phoneNumber: String,
    val passwordFirst: String,
    val passwordSecond: String
)

data class EmptyDialogUiModel(
    val dialogId: String,
    val companion: UserEntity
)