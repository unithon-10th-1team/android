package com.paradise.flickspick.data

import android.content.Context

class TokenManager(context: Context) {

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