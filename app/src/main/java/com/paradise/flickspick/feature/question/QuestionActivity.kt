package com.paradise.flickspick.feature.question

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.paradise.flickspick.databinding.ActivityQuestionBinding
import com.paradise.flickspick.feature.result.ResultActivity
import com.paradise.flickspick.util.startActivityWithAnimation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private val viewModel: QuestViewModel by viewModels()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            if (supportFragmentManager.fragments.size > 1) removeFragment()
            else onBackPressed()
        }
        setStatManager()

        viewModel.questionList.observe(this) {
            startFragment()
        }

        viewModel.completeInfo.observe(this) {
            if (it == null) return@observe
            startActivityWithAnimation<ResultActivity>(
                intentBuilder = { this.putExtra(QUESTION_REQUEST, it) }
            )
            finish()
        }
    }

    private fun setStatManager() {
        viewModel.viewState.observe(this) {
            val isNext = viewModel.currentPage < it

            when (it) {
                1 -> {
                    if (isNext) binding.questState1.startAnimator(true)
                    else binding.questState2.startAnimator(false)
                }

                2 -> {
                    if (isNext) binding.questState2.startAnimator(true)
                    else binding.questState3.startAnimator(false)
                }

                3 -> {
                    if (isNext) binding.questState3.startAnimator(true)
                    else binding.questState4.startAnimator(false)
                }

                4 -> {
                    if (isNext) binding.questState4.startAnimator(true)
                    else binding.questState5.startAnimator(false)
                }

                5 -> {
                    binding.questState5.startAnimator(true)
                }
            }
            viewModel.updateCurrentPage(it)
        }
    }

    private fun View.startAnimator(isCheck: Boolean?) {
        if (isCheck == null) return
        val width = getMaxWidth() / 5
        if (isCheck) {
            this.createScaleWidthAnimator(startWidth = 0, endWidth = width)
        } else {
            this.createScaleWidthAnimator(startWidth = width, endWidth = 0)
        }
    }

    private fun getMaxWidth(): Int {
        val display = applicationContext?.resources?.displayMetrics
        return (display?.widthPixels ?: 0)
    }

    private fun View.createScaleWidthAnimator(duration: Long = 300L, startWidth: Int, endWidth: Int) {
        val valueAnimator = ValueAnimator.ofInt(startWidth, endWidth)
        valueAnimator.addUpdateListener { animation ->
            val newWidth = animation.animatedValue as Int
            val params = this@createScaleWidthAnimator.layoutParams as ViewGroup.LayoutParams
            params.width = newWidth
            this@createScaleWidthAnimator.layoutParams = params
        }
        valueAnimator.duration = duration
        valueAnimator.start()
    }

    private fun startFragment() {
        index++
        val fragmentManager = supportFragmentManager
        val fragment = QuestionFragment(
            index,
            viewModel.questionList.value?.get(index - 1) ?: return,
            viewModel.maxPageSize == index,
            onClickedAnswer = viewModel::updateAnswer
        )

        fragmentManager
            .beginTransaction()
            .add(binding.fragment.id, fragment)
            .commit()


        viewModel.updateViewState(index)
    }

    fun addFragment() {
        index++
        val fragmentManager = supportFragmentManager
        val fragment = QuestionFragment(
            index,
            viewModel.questionList.value?.get(index - 1) ?: return,
            viewModel.maxPageSize == index,
            onClickedAnswer = viewModel::updateAnswer
        )

        fragmentManager
            .beginTransaction()
            .add(binding.fragment.id, fragment)
            .commit()

        viewModel.updateViewState(index)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) removeFragment()
        else super.onBackPressed()
    }

    private fun removeFragment() {
        index--
        val fragmentManager = supportFragmentManager

        fragmentManager
            .beginTransaction()
            .remove(fragmentManager.fragments.last())
            .commit()

        viewModel.updateViewState(index)
    }

    companion object {
        const val QUESTION_REQUEST = "QUESTION_REQUEST"
    }
}