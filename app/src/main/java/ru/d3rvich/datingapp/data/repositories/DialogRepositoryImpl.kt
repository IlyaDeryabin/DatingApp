package ru.d3rvich.datingapp.data.repositories

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import ru.d3rvich.datingapp.domain.entity.DialogEntity
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity
import ru.d3rvich.datingapp.domain.entity.UserEntity
import ru.d3rvich.datingapp.domain.repositories.DialogRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DialogRepositoryImpl : DialogRepository {
    private var currentDialogMessageFlow: MutableSharedFlow<MessageEntity>? = null

    private val users by lazy {
        val messageEntity = MessageEntity(false, "Привет", "")
        return@lazy listOf(
            DialogListItemEntity(
                "0",
                "Роман",
                "https://wfc.tv/f/Lpost/12760/thumb_igor-stepanov-5uuprmzgeym-unsplash.jpg",
                messageEntity.copy(text= "В кино ходишь?")
            ),
            DialogListItemEntity(
                "1",
                "Ольга",
                "https://cdn.fishki.net/upload/post/201502/03/1412777/0f3b760b47333c2b41b7983a05811a91.jpg",
                messageEntity.copy(text = "Нет")
            ),
            DialogListItemEntity(
                "2",
                "Светлана",
                "https://i01.fotocdn.net/s129/7b7ac1aca3ed28ca/user_xl/2913968003.jpg",
                messageEntity.copy(text = "Я подумаю")
            ),
            DialogListItemEntity(
                "3",
                "Мария",
                "https://get.wallhere.com/photo/face-women-model-portrait-depth-of-field-long-hair-photography-black-hair-fashion-hair-nose-Person-skin-head-supermodel-girl-beauty-smile-eye-woman-lady-lip-blond-hairstyle-portrait-photography-photo-shoot-brown-hair-facial-expression-eyebrow-human-body-organ-close-up-Viktoria-Zabiiako-613396.jpg",
                messageEntity.copy(text = "Давай на выходных")
            ),
            DialogListItemEntity(
                "4",
                "Елена",
                "https://i.pinimg.com/originals/2c/79/f1/2c79f13ab17e2c42caecb85aa8d2cde5.jpg",
                messageEntity.copy(text = "С тебя мороженное)")
            ),
            DialogListItemEntity(
                "5",
                "Сергей",
                "https://n1s2.hsmedia.ru/20/cc/9a/20cc9ac5bad1a9fff282a2ed6f741f42/600x600_1_fab283ecb3da565895bd096db2205fae@807x807_0xc0a839a2_8097722801509115373.jpeg",
                messageEntity.copy(text = "Батлу новую уважаешь?")
            ),
            DialogListItemEntity(
                "6",
                "Настя",
                "https://yobte.ru/uploads/posts/2019-11/njashnye-devushki-69-foto-31.jpg",
                messageEntity.copy(text = "Не думаю")
            ),
            DialogListItemEntity(
                "7",
                "Алексей",
                "https://sp-ao.shortpixel.ai/client/q_lqip,ret_wait,w_1500,h_2250/https://www.kirilltigai.com/wp-content/uploads/Muzhskaya-fotosessiya-v-studii-v-Kieve-fotograf-Kirill-Tigaj-_09.jpg",
                messageEntity.copy(text = "Вангард топ если что")
            ),
            DialogListItemEntity(
                "8",
                "Екатерина",
                "https://w-dog.ru/wallpapers/0/92/431934777938831/zhenshhina-mig-15-devushka-bryunetka.jpg",
                messageEntity.copy(text = "Поболтаем?")
            ),
            DialogListItemEntity(
                "9",
                "Настя",
                "https://cdn.fishki.net/upload/post/2019/08/09/3053760/tn/1514484847-milaya-devushka.png",
                messageEntity
            ),
            DialogListItemEntity(
                "10",
                "Света",
                "https://i12.fotocdn.net/s124/a69d11fe09bb0183/gallery_l/2821425357.jpg",
                messageEntity.copy(text = "Хочу ещё раз сходить на паука...")
            ),
            DialogListItemEntity(
                "11",
                "Снежанна",
                "https://99px.ru/sstorage/56/2017/02/image_562802171913416707455.jpg",
                messageEntity
            )
        )
    }

    override suspend fun getDialogList(): List<DialogListItemEntity> {
        delay(500)
        return users
    }

    override suspend fun getDialogBy(id: String): DialogEntity {
        delay(500)
        val user = users[id.toInt()]
        val userEntity = UserEntity(id, user.userName, user.photoLink)
        currentDialogMessageFlow = MutableSharedFlow()
        return DialogEntity(
            id,
            userEntity,
            listOf(
                MessageEntity(
                    isMine = false,
                    text = "Привет!",
                    dispatchTime = "18:22"
                ),
                MessageEntity(
                    isMine = true,
                    text = "Привет",
                    dispatchTime = "18:37"
                ),
                MessageEntity(
                    isMine = false,
                    text = "Пойдём в кино?",
                    dispatchTime = "18:39"
                ),
                MessageEntity(
                    isMine = true,
                    text = "Давай",
                    dispatchTime = "20:16"
                ),
            )
        )
    }

    /** LocalDateTime.now()
    .format(DateTimeFormatter.ofPattern("hh:mm", Locale.getDefault()) */
    override suspend fun sendMessage(dialogId: String, messageEntity: MessageEntity): Result<Unit> {
        currentDialogMessageFlow!!.emit(
            messageEntity.copy(
                isMine = false,
                dispatchTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("hh:mm", Locale.getDefault()))
            )
        )
        return Result.success(Unit)
    }

    override suspend fun getDialogFlow(dialogId: String): Flow<MessageEntity> {
        return currentDialogMessageFlow!!
    }
}