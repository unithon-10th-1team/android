package com.paradise.flickspick.feature.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.paradise.flickspick.R
import com.paradise.flickspick.core.TokenManager
import com.paradise.flickspick.feature.home.HomeActivity
import com.paradise.flickspick.feature.user.LoginActivity
import com.paradise.flickspick.util.startActivityWithAnimation
import dagger.hilt.android.qualifiers.ApplicationContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToActivity(this@MainActivity)
    }

    private fun navigateToActivity(@ApplicationContext context: Context) {
        if (TokenManager(context = context).getToken() != null) {
            startActivityWithAnimation<HomeActivity>()
        }else{
            startActivityWithAnimation<LoginActivity>()
        }
        finish()
    }
}