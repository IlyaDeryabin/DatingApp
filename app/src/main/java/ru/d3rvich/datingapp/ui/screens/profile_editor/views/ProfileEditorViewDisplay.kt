package ru.d3rvich.datingapp.ui.screens.profile_editor.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.ui.common.FocusBox

@Composable
fun ProfileEditorViewDisplay(profile: ProfileEntity?, isError: Boolean = false) {
    val scrollState = rememberScrollState()

    var name by rememberSaveable {
        mutableStateOf(profile?.name ?: "")
    }
    var city by rememberSaveable {
        mutableStateOf(profile?.city ?: "")
    }
    var birthday by rememberSaveable {
        mutableStateOf(profile?.birthday?.time.toString())
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
    FocusBox(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(vertical = 80.dp),
                shape = CircleShape
            ) {
                Text(text = "Select image")
            }
            TextField(value = name, onValueChange = { name = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = city, onValueChange = { city = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = birthday, onValueChange = { birthday = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = description, onValueChange = { description = it })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = fateNumber.toString(), onValueChange = { fateNumber = it.toInt() })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = socionicTypeNumber.toString(),
                onValueChange = { socionicTypeNumber = it.toInt() })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = personalitiesNumber.toString(),
                onValueChange = { personalitiesNumber = it.toInt() })

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditorViewDisplayPreview() {
    ProfileEditorViewDisplay(null)
}