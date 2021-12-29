package ru.d3rvich.datingapp.data.remote

import android.content.SharedPreferences
import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import ru.d3rvich.datingapp.data.constants.ApiConstants
import ru.d3rvich.datingapp.data.constants.AuthConstants
import ru.d3rvich.datingapp.data.dto.AuthDto
import ru.d3rvich.datingapp.data.dto.AuthResponse
import ru.d3rvich.datingapp.data.services.AuthService
import ru.d3rvich.datingapp.domain.exceptions.AuthException
import ru.d3rvich.datingapp.domain.utils.AuthResult
import javax.inject.Inject

private const val TAG = "AuthServiceImpl"

class AuthServiceImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val authSharedPreferences: SharedPreferences
) : AuthService {
    override suspend fun login(authDto: AuthDto): AuthResult {
        return try {
            val authResponse = httpClient.post<AuthResponse>(ApiConstants.AUTH_LOGIN) {
                body = authDto
            }
            Log.d(TAG, "login success: $authResponse")
            putAccessToken(authResponse.accessToken)
            return AuthResult.Success
        } catch (e: RedirectResponseException) { // 3xx codes
            error(e.printStackTrace())
        } catch (e: ClientRequestException) { // 4xx codes
            return AuthResult.Error(AuthException.InvalidLoginOrPassword)
        } catch (e: ServerResponseException) { // 5xx codes
            AuthResult.Error(AuthException.ServerNotResponding)
        } catch (e: Exception) {
            error(e.printStackTrace())
        }
    }

    override suspend fun signup(authDto: AuthDto): AuthResult {
        return try {
            val authResponse = httpClient.post<AuthResponse>(ApiConstants.AUTH_SIGNUP) {
                body = authDto
            }
            Log.d(TAG, "signup success: $authResponse")
            putAccessToken(authResponse.accessToken)
            return AuthResult.Success
        } catch (e: RedirectResponseException) { // 3xx codes
            error(e.printStackTrace())
        } catch (e: ClientRequestException) { // 4xx codes
            return AuthResult.Error(AuthException.ServerNotResponding)
        } catch (e: ServerResponseException) { // 5xx codes
            AuthResult.Error(AuthException.InvalidLoginOrPassword)
        } catch (e: Exception) {
            error(e.printStackTrace())
        }
    }

    private fun putAccessToken(token: String) {
        with(authSharedPreferences.edit()) {
            putString(AuthConstants.ACCESS_TOKEN_KEY, token)
            apply()
        }
    }
}