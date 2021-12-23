package ru.d3rvich.datingapp.ui.screens.dialog.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.d3rvich.datingapp.domain.entity.MessageEntity

@Composable
fun MessageView(modifier: Modifier = Modifier, message: MessageEntity) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when {
            message.isMine -> Alignment.End
            else -> Alignment.Start
        }
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(message),
            backgroundColor = when {
                message.isMine -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.secondary
            }
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = message.text,
                color = when {
                    message.isMine -> MaterialTheme.colors.onPrimary
                    else -> MaterialTheme.colors.onSecondary
                }
            )
        }
        Text(
            text = message.dispatchTime,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun cardShapeFor(message: MessageEntity): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}

@Preview(showBackground = true)
@Composable
fun MessageViewPreviewMine() {
    MessageView(message = MessageEntity(true, "Привет", "3ч"))
}

@Preview(showBackground = true)
@Composable
fun MessageViewPreviewCompanion() {
    MessageView(message = MessageEntity(false, "Привет", "3ч"))
}