package com.paradise.flickspick.feature.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paradise.flickspick.R
import com.paradise.flickspick.databinding.ActivityLoginBinding
import com.paradise.flickspick.feature.onboard.OnBoardActivity
import com.paradise.flickspick.util.startActivityWithAnimation

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUp.setOnClickListener {
            startActivityWithAnimation<SignUpActivity>()
        }


    }
}