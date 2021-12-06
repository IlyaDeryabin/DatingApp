package ru.d3rvich.datingapp.ui.screens.profile_editor.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.ui.common.FocusBox

@Composable
fun ProfileEditorViewDisplay(profile: ProfileEntity?) {
    val scrollState = rememberScrollState()
    var name by rememberSaveable {
        mutableStateOf(profile?.name ?: "")
    }
    var city by rememberSaveable {
        mutableStateOf(profile?.city ?: "")
    }
    var birthday by rememberSaveable {
        mutableStateOf(profile?.birthday.toString())
    }
    var description by rememberSaveable {
        mutableStateOf(profile?.description ?: "")
    }
    var fateNumber by rememberSaveable {
        mutableStateOf(profile?.fateNumber ?: 0)
    }
    var socionicTypeNumber by rememberSaveable {
        mutableStateOf(profile?.socionicTypeNumber ?: 0)
    }
    var personalitiesNumber by rememberSaveable {
        mutableStateOf(profile?.personalitiesNumber ?: 0)
    }
    var imageLink by rememberSaveable {
        mutableStateOf(profile?.imageLink ?: "")
    }
    FocusBox(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(vertical = 80.dp),
                shape = CircleShape
            ) {
                Text(text = "Select image")
            }
            Text(
                text = "Name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                singleLine = true
            )
            Text(
                text = "City",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = city, onValueChange = { city = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                singleLine = true
            )
            Text(
                text = "Birthday",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = birthday, onValueChange = { birthday = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                singleLine = true
            )
            Text(
                text = "Description",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = description, onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            // Switch to dropdown menu
            TextField(
                value = fateNumber.toString(), onValueChange = { fateNumber = it.toInt() },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center)
            )

            // Switch to dropdown menu
            TextField(
                value = socionicTypeNumber.toString(),
                onValueChange = { socionicTypeNumber = it.toInt() },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center)
            )

            // Switch to dropdown menu
            TextField(
                value = personalitiesNumber.toString(),
                onValueChange = { personalitiesNumber = it.toInt() },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditorViewDisplayPreview() {
    ProfileEditorViewDisplay(null)
}