package com.paradise.flickspick.feature.user

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.paradise.flickspick.R
import com.paradise.flickspick.data.TokenManager
import com.paradise.flickspick.databinding.ActivityLoginBinding
import com.paradise.flickspick.feature.onboard.OnBoardActivity
import com.paradise.flickspick.retrofit.RetrofitClient
import com.paradise.flickspick.retrofit.model.LoginUserData
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
        binding.buttonLogin.setOnClickListener {
        val userLoginData = LoginUserData(username = binding.mainIdField.text.toString(), password = binding.mainPasswordField.text.toString())
            logIn(userLoginData)
        }
    }

    fun logIn(user: LoginUserData) = lifecycleScope.launch {
        runCatching {
            RetrofitClient.instance.login(user)
        }.onSuccess { token ->
            TokenManager(this@LoginActivity).saveToken(token = token.data.token)

        }.onFailure {
            Toast.makeText(this@LoginActivity, "잘못된 요청입니다!", Toast.LENGTH_SHORT).show()
        }
    }
}