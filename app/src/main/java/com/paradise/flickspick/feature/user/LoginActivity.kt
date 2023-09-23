package com.paradise.flickspick.feature.user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.paradise.flickspick.core.TokenManager
import com.paradise.flickspick.databinding.ActivityLoginBinding
import com.paradise.flickspick.feature.main.select.SelectOttActivity
import com.paradise.flickspick.retrofit.api.ApiService
import com.paradise.flickspick.retrofit.model.response.LoginUserData
import com.paradise.flickspick.util.startActivityWithAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var instance: ApiService

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUp.setOnClickListener {
            startActivityWithAnimation<SignUpActivity>() // recMovies
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
            instance.login(user)
        }.onSuccess { token ->
            TokenManager(context = this@LoginActivity).saveToken(
                token.data.token
            )
            navigateToSelectOtt()
        }.onFailure {
            Toast.makeText(this@LoginActivity, "아이디와 비밀번호를 확인해주세요 ${it}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSelectOtt() {
        startActivityWithAnimation<SelectOttActivity>()
        finish()
    }
}