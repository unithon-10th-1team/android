package com.paradise.flickspick.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paradise.flickspick.R
import com.paradise.flickspick.feature.result.ResultActivity
import com.paradise.flickspick.feature.user.LoginActivity
import com.paradise.flickspick.feature.user.SignUpActivity
import com.paradise.flickspick.util.startActivityWithAnimation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToActivity()
    }

    private fun navigateToActivity() {
        startActivityWithAnimation<SignUpActivity>()
    }
}