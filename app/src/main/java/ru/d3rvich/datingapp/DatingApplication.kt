package ru.d3rvich.datingapp

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import ru.d3rvich.datingapp.data.constants.AuthConstants

@HiltAndroidApp
class DatingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val authSharedPreference: SharedPreferences =
            applicationContext.getSharedPreferences(AuthConstants.SHARED_PREF_KEY, MODE_PRIVATE)
        if (authSharedPreference.contains(AuthConstants.ACCESS_TOKEN_KEY)) {
            with(authSharedPreference.edit()) {
                remove(AuthConstants.ACCESS_TOKEN_KEY)
                apply()
            }
        }
    }
}