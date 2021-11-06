package ru.d3rvich.datingapp.domain.repositories

import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity

interface DialogRepository {
    suspend fun getDialogList(): List<DialogListItemEntity>

    suspend fun getDialogBy(id: String): DialogEntity
}