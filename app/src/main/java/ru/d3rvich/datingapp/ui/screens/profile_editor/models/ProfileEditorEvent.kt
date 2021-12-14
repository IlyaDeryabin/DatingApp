package ru.d3rvich.datingapp.ui.screens.profile_editor.models

import ru.d3rvich.datingapp.domain.entity.ProfileEntity

sealed class ProfileEditorEvent {
    class OnSaveButtonClicked(val profile: ProfileEntity) : ProfileEditorEvent()
}