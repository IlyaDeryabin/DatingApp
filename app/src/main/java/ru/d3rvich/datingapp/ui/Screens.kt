package ru.d3rvich.datingapp.ui

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login")
}