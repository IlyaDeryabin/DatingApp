package ru.d3rvich.datingapp.ui.screens.dialog_list.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import ru.d3rvich.datingapp.domain.entity.DialogListItemEntity
import ru.d3rvich.datingapp.domain.entity.MessageEntity

@ExperimentalCoilApi
@Composable
fun DialogListItem(dialogListItemEntity: DialogListItemEntity, onItemClicked: (String) -> Unit) {
    Box(modifier = Modifier
        .clickable { onItemClicked(dialogListItemEntity.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imagePainter = rememberImagePainter(
                data = dialogListItemEntity.photoLink,
                builder = {
                    crossfade(true)
                }
            )
            Image(
                painter = imagePainter,
                contentDescription = "${dialogListItemEntity.userName}'s photo",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = imagePainter.state is ImagePainter.State.Loading,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
            Box {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Text(text = dialogListItemEntity.userName, style = MaterialTheme.typography.h6)
                    Text(
                        text = dialogListItemEntity.lastMassage.massage,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
                .align(Alignment.BottomCenter)
        )
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DialogListItemPreview() {
    val messageEntity = MessageEntity("0", "Привет. Почему одна? Сейчас будет два.", "")
    val dialogListItemEntity = DialogListItemEntity(
        "0",
        "Роман",
        "https://avatars.mds.yandex.net/get-zen_doc/61319/pub_5a043bdead0f224ec2034e67_5a04405d79885e58f3fd095e/scale_1200",
        messageEntity
    )
    DialogListItem(dialogListItemEntity = dialogListItemEntity, onItemClicked = {})
}