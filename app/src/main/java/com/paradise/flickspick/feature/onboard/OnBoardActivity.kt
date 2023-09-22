package com.paradise.flickspick.feature.onboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.paradise.flickspick.common.component.PrimaryLargeButton
import com.paradise.flickspick.common.layout.TopAppBar

class OnBoardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(leadingContent = {  }, text = "hello") {

                }
                PrimaryLargeButton(text = "test", enabled = true) {

                }
            }
        }
    }
}