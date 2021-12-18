package ru.d3rvich.datingapp.ui.screens.main.models

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.domain.entity.ProfileEntity

sealed class MainScreenState {
    object Loading : MainScreenState()
    class Loaded(val profile: ProfileEntity) : MainScreenState()
    class Error(@StringRes val messageResId: Int) : MainScreenState()
}
