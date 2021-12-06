package ru.d3rvich.datingapp.ui

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login")
    object SignUpScreen : Screens("sign_up")
    object DialogListScreen : Screens("dialog_list")
    object DialogScreen : Screens("dialog")
    object EmptyProfileEditor : Screens("profile_editor_empty")
}