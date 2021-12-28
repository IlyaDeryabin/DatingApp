package ru.d3rvich.datingapp.domain.utils

sealed class AuthResult {
    object Success : AuthResult()
    class Error(throwable: Throwable? = null) : AuthResult()
}
