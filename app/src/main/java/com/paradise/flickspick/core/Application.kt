package com.paradise.flickspick.core

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PickApplication: Application() {
}

class TokenManager(val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("token_preferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("access_token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }
}