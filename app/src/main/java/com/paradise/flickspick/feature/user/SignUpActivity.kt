package com.paradise.flickspick.feature.user

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.paradise.flickspick.databinding.ActivitySignUpBinding
import com.paradise.flickspick.feature.user.view.UserIdentifyView
import com.paradise.flickspick.feature.user.view.UserNickNameView
import com.paradise.flickspick.feature.user.view.UserPassWordView
import com.paradise.flickspick.retrofit.RetrofitClient
import com.paradise.flickspick.retrofit.model.RegisterUserData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userViewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameView = UserNickNameView(this@SignUpActivity)
        val idView = UserIdentifyView(this@SignUpActivity)
        val passwdView = UserPassWordView(this@SignUpActivity)
        var pageIndex = 0

        var userNameValue = ""
        var userIdValue = ""
        var userPasswordValue = ""

        binding.buttonNext.isEnabled = false

        userViewModel = ViewModelProvider(this)[UserDataViewModel::class.java]

        fun fadeInAnimation(view: View) {
            val translateAnimator = ObjectAnimator.ofFloat(
                view,
                "translationX",
                view.translationX + 500,
                view.translationX
            )

            val fadeOutAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(translateAnimator, fadeOutAnimator)
            animatorSet.duration = 500 // 1초 동안 실행

            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

            })
            animatorSet.start()
        }

        userViewModel.userNameState.observe(this, Observer { state ->
            when (state) {
                UserDataViewModel.State.FULL -> {
                    if (pageIndex == 0 && nameView.name.isEmpty()) {
                        binding.buttonNext.isEnabled = true
                    }
                }

                UserDataViewModel.State.EMPTY -> {
                    if (pageIndex == 0 && idView.id.isEmpty()) {
                        binding.buttonNext.isEnabled = false
                    }
                }
            }
        })

        userViewModel.userIDState.observe(this, Observer { state ->
            when (state) {
                UserDataViewModel.State.FULL -> {
                    if (pageIndex == 1 && passwdView.passWord.isEmpty()) {
                        binding.buttonNext.isEnabled = true
                    }
                }

                UserDataViewModel.State.EMPTY -> {
                    if (pageIndex == 1) {
                        binding.buttonNext.isEnabled = false
                    }
                }
            }
        })

        userViewModel.userPasswordState.observe(this, Observer { state ->
            when (state) {
                UserDataViewModel.State.FULL -> {
                    if (pageIndex == 2) {
                        binding.buttonNext.isEnabled = true
                    }
                }

                UserDataViewModel.State.EMPTY -> {
                    if (pageIndex == 2) {
                        binding.buttonNext.isEnabled = false
                    }
                }
            }
        })

        binding.buttonNext.setOnClickListener {
            when (pageIndex) {
                0 -> {
                    //userNameValue = nameView.name
                    userNameValue = userViewModel.name
                    forward(binding.nameLayout, binding.idLayout)
                    pageIndex++
                }

                1 -> {
                    userIdValue = userViewModel.id
                    forward(binding.idLayout, binding.passwdLayout)
                    pageIndex++
                }

                2 -> {
                    userPasswordValue = userViewModel.passwd
                    val user = RegisterUserData(username = userIdValue, nickname = userNameValue, password = userPasswordValue)
                    registerUser(user)
                    finish()
                }
            }
            binding.buttonNext.isEnabled = false
        }

        binding.imageView.setOnClickListener {
            when (pageIndex) {
                0 -> {
                    finish()
                }

                1 -> {
                    back(binding.idLayout, binding.nameLayout)
                    pageIndex--
                }

                2 -> {
                    back(binding.passwdLayout, binding.idLayout)
                    pageIndex--
                }
            }
        }
    }

    fun forward(view: View, nextView: View) {
        val translateAnimator1 =
            ObjectAnimator.ofFloat(view, "translationX", view.translationX, view.translationX - 500)
        val fadeOutAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)

        val translateAnimator2 = ObjectAnimator.ofFloat(
            nextView,
            "translationX",
            nextView.translationX + 500,
            nextView.translationX
        )
        val fadeOutAnimator2 = ObjectAnimator.ofFloat(nextView, "alpha", 0f, 1f)

        val animatorSet1 = AnimatorSet()
        animatorSet1.playTogether(translateAnimator1, fadeOutAnimator1)
        animatorSet1.duration = 500

        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(translateAnimator2, fadeOutAnimator2)
        animatorSet2.duration = 500

        animatorSet1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                view.visibility = View.GONE
                val translateAnimator3 =
                    ObjectAnimator.ofFloat(view, "translationX", view.translationX, view.translationX + 500)
                translateAnimator3.start()

                nextView.visibility = View.VISIBLE
                animatorSet2.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
        animatorSet1.start()
    }

    fun back(view: View, nextView: View) {
        val translateAnimator1 =
            ObjectAnimator.ofFloat(view, "translationX", view.translationX, view.translationX + 500)
        val fadeOutAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)

        val translateAnimator2 = ObjectAnimator.ofFloat(
            nextView,
            "translationX",
            nextView.translationX - 500,
            nextView.translationX
        )
        val fadeOutAnimator2 = ObjectAnimator.ofFloat(nextView, "alpha", 0f, 1f)

        val animatorSet1 = AnimatorSet()
        animatorSet1.playTogether(translateAnimator1, fadeOutAnimator1)
        animatorSet1.duration = 500 // 1초 동안 실행

        val animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(translateAnimator2, fadeOutAnimator2)
        animatorSet2.duration = 500 // 1초 동안 실행

        animatorSet1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                val translateAnimator3 =
                    ObjectAnimator.ofFloat(view, "translationX", view.translationX, view.translationX - 500)
                translateAnimator3.start()

                view.visibility = View.GONE
                nextView.visibility = View.VISIBLE
                animatorSet2.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
        animatorSet1.start()
    }

    private fun registerUser(user: RegisterUserData) = lifecycleScope.launch {
        RetrofitClient.instance.registerUser(user)
    }

}