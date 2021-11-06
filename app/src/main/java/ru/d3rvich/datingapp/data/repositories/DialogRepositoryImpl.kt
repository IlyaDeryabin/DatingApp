package ru.d3rvich.datingapp.data.repositories

import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.domain.entity.UserEntity
import ru.d3rvich.datingapp.domain.repositories.DialogRepository

class DialogRepositoryImpl : DialogRepository {
    override suspend fun getDialogList(): List<DialogListItemEntity> {
        val messageEntity = MessageEntity("0", "Привет", "")
        return listOf(
            DialogListItemEntity("0", "Роман", "", messageEntity),
            DialogListItemEntity("1", "Олег", "", messageEntity),
            DialogListItemEntity("2", "Олег", "", messageEntity),
            DialogListItemEntity("3", "Олег", "", messageEntity),
            DialogListItemEntity("4", "Елена", "", messageEntity),
            DialogListItemEntity("5", "Сергей", "", messageEntity),
            DialogListItemEntity("6", "Настя", "", messageEntity),
            DialogListItemEntity("7", "Роман", "", messageEntity),
            DialogListItemEntity("8", "Роман", "", messageEntity),
        )
    }

    override suspend fun getDialogBy(id: String): DialogEntity {
        val userEntity = UserEntity("1", "Роман", "")
        return DialogEntity(id, userEntity, emptyList())
    }
}