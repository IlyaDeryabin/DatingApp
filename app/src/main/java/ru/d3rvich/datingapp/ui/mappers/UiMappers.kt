package ru.d3rvich.datingapp.ui.mappers

import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.ui.model.SignupUiModel
import java.time.LocalDate

fun SignupUiModel.toAuthEntity(): AuthEntity {
    require(passwordFirst == passwordSecond) {
        "Пароли не совпадают. $passwordFirst и $passwordSecond должны быть равыны."
    }
    return AuthEntity(phoneNumber, passwordFirst)
}

fun LocalDate.toDateEntity(): DateEntity {
    return DateEntity(day = dayOfMonth, month = monthValue, year = year)
}

fun DateEntity.toLocalDate(): LocalDate {
    return LocalDate.of(year, month, day)
}