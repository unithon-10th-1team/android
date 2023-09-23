package com.paradise.flickspick.feature.question

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.paradise.flickspick.R
import com.paradise.flickspick.databinding.FragmentQuestionBinding


class QuestionFragment(private val index: Int) : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    val binding  get() = checkNotNull(_binding)

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

        binding.button1.setOnClickListener {
            (requireActivity() as QuestionActivity).addFragment()
        }

        binding.button2.setOnClickListener {
            (requireActivity() as QuestionActivity).addFragment()
        }

        val wordtoSpan = SpannableStringBuilder( "질문 $index. 당신은 \n어느 나라 사람입니까!")

        wordtoSpan.setSpan(ForegroundColorSpan(Color.parseColor("#00FF85")), 0, 4 + (index.toString().length), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        binding.textView.text = wordtoSpan
    }

    override fun onStop() {
        val slideInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
        binding.root.startAnimation(slideInAnimation)
        super.onStop()
    }
}