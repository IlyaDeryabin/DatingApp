package ru.d3rvich.datingapp.domain.entity

/**
 * Data классы-сущности domain слоя
 * */
data class AuthEntity(val phoneNumber: String, val password: String)

data class UserEntity(val id: String, val name: String, val photoLink: String)

data class DialogListItemEntity(
    val id: String,
    val userName: String,
    val photoLink: String,
    val lastMassage: MessageEntity
)

data class DialogEntity(val dialogId: String, val companion: UserEntity, val messages: List<MessageEntity>)

data class MessageEntity(val senderId: String, val massage: String, val dispatchTime: String)