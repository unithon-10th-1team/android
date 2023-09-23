package com.paradise.flickspick.feature.question

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.paradise.flickspick.R
import com.paradise.flickspick.databinding.FragmentQuestionBinding


class QuestionFragment(
    private val index: Int,
    private val question: Question,
    private val isLast: Boolean,
    private val onClickedAnswer: (Int, Int, Int) -> Unit
) : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val slideInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
        binding.root.startAnimation(slideInAnimation)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button1.apply {
            setOnClickListener {
                if (isLast.not()) {
                    (requireActivity() as QuestionActivity).addFragment()
                }
                onClickedAnswer(index-1, question.answers[0].questionId, question.answers[0].id)
            }
            text = question.answers[0].answer
        }

        binding.button2.apply {
            setOnClickListener {
                if (isLast.not()) {
                    (requireActivity() as QuestionActivity).addFragment()
                }
                onClickedAnswer(index-1, question.answers[0].questionId, question.answers[0].id)
            }
            text = question.answers[1].answer
        }

        val wordtoSpan = SpannableStringBuilder("질문 $index. ${question.question}")

        wordtoSpan.setSpan(
            ForegroundColorSpan(Color.parseColor("#00FF85")),
            0,
            4 + (index.toString().length),
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.textView.text = wordtoSpan
    }

    override fun onStop() {
        val slideInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
        binding.root.startAnimation(slideInAnimation)
        super.onStop()
    }
}