package com.paradise.flickspick.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paradise.flickspick.R
import com.paradise.flickspick.common.component.PrimaryLargeButton
import com.paradise.flickspick.feature.onboard.OnBoardActivity
import com.paradise.flickspick.feature.result.ResultActivity
import com.paradise.flickspick.feature.user.LoginActivity
import com.paradise.flickspick.util.finishWithAnimation
import com.paradise.flickspick.util.startActivityWithAnimation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToActivity()
    }

    private fun navigateToActivity() {
        startActivityWithAnimation<ResultActivity>()
    }
}