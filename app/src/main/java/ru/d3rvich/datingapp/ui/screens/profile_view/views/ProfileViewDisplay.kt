package ru.d3rvich.datingapp.ui.screens.profile_view.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

@Composable
fun ProfileViewDisplay(
    profile: ProfileEntity,
    onBackPressed: () -> Unit,
    onEditProfileClicked: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile")
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEditProfileClicked) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit profile")
                    }
                }
            )
        }) {
        Text(text = "Profile view")
    }
}