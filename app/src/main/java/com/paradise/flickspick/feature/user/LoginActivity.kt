package com.paradise.flickspick.feature.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.paradise.flickspick.R
import com.paradise.flickspick.databinding.ActivityLoginBinding
import com.paradise.flickspick.feature.onboard.OnBoardActivity
import com.paradise.flickspick.retrofit.RetrofitClient
import com.paradise.flickspick.retrofit.model.RegisterUserData
import com.paradise.flickspick.util.startActivityWithAnimation
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun logIn(user: RegisterUserData) = lifecycleScope.launch {
        RetrofitClient.instance.registerUser(user)
    }

}