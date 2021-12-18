package ru.d3rvich.datingapp.ui

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.R

sealed class Screen(val route: String, @StringRes titleResId: Int? = 0) {
    object MainScreen : Screen("main")
    object LoginScreen : Screen("login")
    object SignUpScreen : Screen("sign_up")
    object DialogScreen : Screen("dialog")
    object EmptyProfileEditor : Screen("profile_editor_empty")
    object Settings : Screen("setting")
}

sealed class DrawerScreen(val route: String, @StringRes val titleResId: Int = 0) {
    object DialogListScreen : DrawerScreen("dialog_list", R.string.messages)
    object PairSearchScreen : DrawerScreen("pair_search")
    object ProfileScreen : DrawerScreen("profile")
}