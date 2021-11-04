package ru.d3rvich.datingapp.ui.screens.sing_up_screen.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.AuthEntity
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpViewState

@Composable
fun SignUpDisplay(
    viewState: SignUpViewState,
    onSignUpButtonClicked: (AuthEntity) -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onLoginButtonClicked, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(text = "Вход")
        }
    }
}