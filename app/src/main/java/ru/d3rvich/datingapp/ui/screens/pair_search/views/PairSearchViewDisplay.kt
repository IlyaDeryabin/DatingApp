package ru.d3rvich.datingapp.ui.screens.pair_search.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

@Composable
fun PairSearchViewDisplay(
    currentCandidate: ProfileEntity,
    onLike: () -> Unit,
    onDislike: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val painter = rememberImagePainter(data = currentCandidate.imageLink)
        Image(
            painter = painter,
            contentDescription = currentCandidate.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray.copy(alpha = 0.5f))
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = currentCandidate.name, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${currentCandidate.age}, ${currentCandidate.city}"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray.copy(alpha = 0.4f))
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val buttonIconSize = 60.dp
                IconButton(onClick = onDislike) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_heart_broken_24),
                        contentDescription = "Dislike",
                        tint = MaterialTheme.colors.secondary,
                        modifier = Modifier.size(buttonIconSize)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = currentCandidate.socionicTypeNumber.toString(),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = currentCandidate.fateNumber.toString(),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = currentCandidate.zodiacId.toString())
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = currentCandidate.personalitiesNumber.toString())
                }
                IconButton(onClick = onLike) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = "Like",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(buttonIconSize)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PairSearchViewDisplayPreview() {
    val candidate = ProfileEntity(
        name = "Name",
        city = "City",
        age = 22,
        birthday = DateEntity(day = 24, month = 12, year = 1999),
        description = "Description",
        zodiacId = 4,
        fateNumber = 8,
        socionicTypeNumber = 1,
        personalitiesNumber = 1,
        imageLink = ""
    )
    PairSearchViewDisplay(currentCandidate = candidate, {}, {})
}