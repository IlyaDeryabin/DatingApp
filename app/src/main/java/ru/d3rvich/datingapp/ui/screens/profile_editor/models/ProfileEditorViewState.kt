package ru.d3rvich.datingapp.ui.screens.profile_editor.models

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

sealed class ProfileEditorViewState {
    object Idle : ProfileEditorViewState()
    object SaveInProgress : ProfileEditorViewState()
    object EmptyProfile : ProfileEditorViewState()
    class Editor(val profile: ProfileEntity) : ProfileEditorViewState()
    class Error(@StringRes val message: Int) : ProfileEditorViewState()
}
