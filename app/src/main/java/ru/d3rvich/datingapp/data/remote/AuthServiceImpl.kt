package ru.d3rvich.datingapp.data.remote

import android.content.SharedPreferences
import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*
import ru.d3rvich.datingapp.data.constants.ApiConstants
import ru.d3rvich.datingapp.data.constants.AuthConstants
import ru.d3rvich.datingapp.data.dto.AuthDto
import ru.d3rvich.datingapp.data.dto.AuthResponse
import ru.d3rvich.datingapp.data.services.AuthService
import javax.inject.Inject

private const val TAG = "AuthServiceImpl"

class AuthServiceImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val authSharedPreferences: SharedPreferences
) : AuthService {
    override suspend fun login(authDto: AuthDto) {
        httpClient.use { client ->
            val authResponse = client.post<AuthResponse>(ApiConstants.AUTH_LOGIN) {
                body = authDto
            }
            Log.d(TAG, "login success: $authResponse")
            putAccessToken(authResponse.accessToken)
        }
    }

    override suspend fun signup(authDto: AuthDto) {
        httpClient.use { client ->
            val authResponse = client.post<AuthResponse>(ApiConstants.AUTH_SIGNUP) {
                body = authDto
            }
            Log.d(TAG, "signup success: $authResponse")
            putAccessToken(authResponse.accessToken)
        }
    }

    private fun putAccessToken(token: String) {
        with(authSharedPreferences.edit()) {
            putString(AuthConstants.ACCESS_TOKEN_KEY, token)
            apply()
        }
    }
}