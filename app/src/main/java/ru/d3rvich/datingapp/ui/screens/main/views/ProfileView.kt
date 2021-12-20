package ru.d3rvich.datingapp.ui.screens.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    profileEntity: ProfileEntity,
    onUserProfileClicked: () -> Unit
) {
    val imagePainter = rememberImagePainter(data = profileEntity.imageLink,
        builder = {
            crossfade(true)
        })
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onUserProfileClicked()
            }
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Your photo",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "${profileEntity.name}, ${profileEntity.age}",
                modifier = Modifier.padding(bottom = 2.dp),
                fontSize = 16.sp
            )
            Text(
                text = profileEntity.city,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileViewPreview() {
    val profile = ProfileEntity(
        "Имя",
        "Ёбург",
        0,
        DateEntity(0, 0, 0),
        "Описание",
        0,
        0,
        0,
        0,
        "https://static.probusiness.io/n/03/d/38097027_439276526579800_2735888197547458560_n.jpg"
    )
    ProfileView(profileEntity = profile) {
    }
}