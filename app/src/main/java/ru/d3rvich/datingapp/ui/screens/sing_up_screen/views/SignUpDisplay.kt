package ru.d3rvich.datingapp.ui.screens.sing_up_screen.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.BuildConfig
import ru.d3rvich.datingapp.ui.model.SingUpUiModel
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.models.SignUpViewState

@Composable
fun SignUpDisplay(
    viewState: SignUpViewState,
    onSignUpButtonClicked: (SingUpUiModel) -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Вход", modifier = Modifier
            .padding(8.dp)
            .clickable { onLoginButtonClicked() }
            .align(Alignment.TopEnd),
            fontStyle = FontStyle.Italic
        )
        if (BuildConfig.DEBUG) {
            Text( // for debug only
                text = viewState.javaClass.simpleName,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var phoneNumber by rememberSaveable {
                mutableStateOf("")
            }
            var passwordFirst by rememberSaveable {
                mutableStateOf("")
            }
            var passwordSecond by rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = phoneNumber, onValueChange = { phoneNumber = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = passwordFirst, onValueChange = { passwordFirst = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = passwordSecond, onValueChange = { passwordSecond = it })
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    if (viewState is SignUpViewState.SignUpDisplay) {
                        val signUpUiModel =
                            SingUpUiModel(phoneNumber, passwordFirst, passwordSecond)
                        onSignUpButtonClicked(signUpUiModel)
                    }
                },
                modifier = Modifier.animateContentSize()
            ) {
                if (viewState is SignUpViewState.InProgress) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Text("Зарегистрироваться")
                }
            }
        }
    }
}