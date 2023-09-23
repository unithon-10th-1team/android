package com.paradise.flickspick.feature.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.paradise.flickspick.data.TokenManager
import com.paradise.flickspick.core.PickApplication
import com.paradise.flickspick.databinding.ActivityLoginBinding
import com.paradise.flickspick.feature.main.select.SelectOttActivity
import com.paradise.flickspick.retrofit.RetrofitClient
import com.paradise.flickspick.retrofit.model.LoginUserData
import com.paradise.flickspick.util.startActivityWithAnimation
import kotlinx.coroutines.launch

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
            val userLoginData = LoginUserData(
                username = binding.mainIdField.text.toString(),
                password = binding.mainPasswordField.text.toString()
            )
            logIn(userLoginData)
        }
    }

    private fun logIn(user: LoginUserData) = lifecycleScope.launch {
        runCatching {
            RetrofitClient.instance.login(user)
        }.onSuccess { token ->
            PickApplication().getTokenManager().saveToken(
                token.data.token
            )
            navigateToSelectOtt()
        }.onFailure {
            Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSelectOtt() {
        startActivityWithAnimation<SelectOttActivity>()
    }
}