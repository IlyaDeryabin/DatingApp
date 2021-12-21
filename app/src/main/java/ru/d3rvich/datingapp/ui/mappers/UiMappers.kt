package ru.d3rvich.datingapp.ui.mappers

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.ui.model.SingUpUiModel

fun SingUpUiModel.toAuthEntity(): AuthEntity {
    if (passwordFirst == passwordSecond) {
        return AuthEntity(phoneNumber, passwordFirst)
    } else {
        error("Пароли не совпадают. $passwordFirst и $passwordSecond должны быть равыны.")
    }
}