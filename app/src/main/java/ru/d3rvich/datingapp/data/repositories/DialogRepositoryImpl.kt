package ru.d3rvich.datingapp.data.repositories

import kotlinx.coroutines.delay
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.domain.entity.UserEntity
import ru.d3rvich.datingapp.domain.repositories.DialogRepository

class DialogRepositoryImpl : DialogRepository {
    override suspend fun getDialogList(): List<DialogListItemEntity> {
        delay(1000)
        val photoLink = "https://picsum.photos/300/300"
        val messageEntity = MessageEntity(false, "Привет", "")
        return listOf(
            DialogListItemEntity("0", "Роман", photoLink, messageEntity),
            DialogListItemEntity("1", "Олег", photoLink, messageEntity),
            DialogListItemEntity("2", "Олег", photoLink, messageEntity),
            DialogListItemEntity("3", "Олег", photoLink, messageEntity),
            DialogListItemEntity("4", "Елена", photoLink, messageEntity),
            DialogListItemEntity("5", "Сергей", photoLink, messageEntity),
            DialogListItemEntity("6", "Настя", photoLink, messageEntity),
            DialogListItemEntity("7", "Роман", photoLink, messageEntity),
            DialogListItemEntity("8", "Роман", photoLink, messageEntity),
            DialogListItemEntity("9", "Настя", photoLink, messageEntity),
            DialogListItemEntity("10", "Роман", photoLink, messageEntity),
            DialogListItemEntity("11", "Роман", photoLink, messageEntity)
        )
    }

    override suspend fun getDialogBy(id: String): DialogEntity {
        delay(1000)
        val userEntity = UserEntity("1", "Роман", "")
        return DialogEntity(id, userEntity, emptyList())
    }
}