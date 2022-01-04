package ru.d3rvich.datingapp.data.mappers

import ru.d3rvich.datingapp.data.dto.AuthDto
import ru.d3rvich.datingapp.domain.entity.AuthEntity

/**
 * Мапперы над entities data слоя
 * */
fun AuthEntity.toAuthDto(): AuthDto {
    return AuthDto(phoneNumber, password)
}