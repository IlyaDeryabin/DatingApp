package ru.d3rvich.datingapp.domain.utils

import ru.d3rvich.datingapp.domain.exceptions.AuthException

sealed class AuthResult {
    object Success : AuthResult()
    class Error(exception: AuthException) : AuthResult()
}
