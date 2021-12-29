package ru.d3rvich.datingapp.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.d3rvich.datingapp.data.constants.AuthConstants
import kotlinx.serialization.json.Json as SerializationJson

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(authSharedPreferences: SharedPreferences): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(SerializationJson {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            defaultRequest {
                if (authSharedPreferences.contains(AuthConstants.ACCESS_TOKEN_KEY)) {
                    val token = "Bearer: ${
                        authSharedPreferences.getString(
                            AuthConstants.ACCESS_TOKEN_KEY,
                            null
                        )
                    }"
                    header("Authorization", token)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}