package ru.d3rvich.datingapp.domain.exceptions

sealed class AuthException: Exception() {
    object UserAlreadyExist: AuthException()
    object InvalidLoginOrPassword: AuthException()
    object ServerNotResponding: AuthException()
}
