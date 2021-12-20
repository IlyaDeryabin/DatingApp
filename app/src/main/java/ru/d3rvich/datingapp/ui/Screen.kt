package ru.d3rvich.datingapp.ui

import androidx.annotation.StringRes
import ru.d3rvich.datingapp.R

sealed class Screen(val route: String, @StringRes val titleResId: Int? = null) {
    object MainScreen : Screen("main")
    object LoginScreen : Screen("login")
    object SignUpScreen : Screen("sign_up")
    object DialogScreen : Screen("dialog")
    object ProfileScreen : DrawerScreen("profile", R.string.profile)
    object EmptyProfileEditor : Screen("profile_editor_empty")
    object Settings : Screen("setting", R.string.settings)
}

sealed class DrawerScreen(val route: String, @StringRes val titleResId: Int) {
    object DialogListScreen : DrawerScreen("dialog_list", R.string.messages)
    object PairSearchScreen : DrawerScreen("pair_search", R.string.find_fate)
}