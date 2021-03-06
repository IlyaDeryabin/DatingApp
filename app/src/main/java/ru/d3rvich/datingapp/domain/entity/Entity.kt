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

data class DialogEntity(
    val dialogId: String,
    val companion: UserEntity,
    val messages: List<MessageEntity>
)

data class MessageEntity(val isMine: Boolean, val text: String, val dispatchTime: String)

data class ProfileEntity(
    val name: String,
    val city: String,
    val age: Int,
    val birthday: DateEntity,
    val description: String,
    val zodiacId: Int,
    val fateNumber: Int,
    val socionicTypeNumber: Int,
    val personalitiesNumber: Int,
    val imageLink: String
)

data class DateEntity(val day: Int, val month: Int, val year: Int) {
    override fun toString(): String {
        return "$day.$month.$year"
    }

    companion object {
        /**
         * Принимает строку в формате "dd.MM.yyyy"
         * @param date строка в формате "dd.MM.yyyy"
         * */
        fun parse(date: String): DateEntity {
            val parsedString = date.split('.')
            require(parsedString.size == 3)
            val dayOfMonth = parsedString[0]
            val month = parsedString[1]
            val year = parsedString[2]
            require(dayOfMonth.length <= 2 && month.length <= 2 && year.length <= 4)
            return DateEntity(
                dayOfMonth.toInt(),
                month.toInt(),
                year.toInt()
            )
        }
    }
}