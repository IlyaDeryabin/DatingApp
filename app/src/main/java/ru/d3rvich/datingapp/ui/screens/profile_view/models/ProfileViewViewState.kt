package ru.d3rvich.datingapp.ui.screens.profile_view.models

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

sealed class ProfileViewViewState {
    object Loading : ProfileViewViewState()
    class Display(val profile: ProfileEntity) : ProfileViewViewState()
    class Error(@StringRes message: Int) : ProfileViewViewState()
}
