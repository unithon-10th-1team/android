package com.paradise.flickspick.feature.main.select

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.paradise.flickspick.databinding.ActivitySelectOttBinding


class SelectOttActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectOttBinding

    private val viewModel: SelectOttViewModel by viewModels()

    private val adapter by lazy {
        SelectOttAdapter { item ->
            viewModel.updateOttList(item)
            viewModel.updateNextBtnState()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectOttBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvOtt.adapter = adapter

        val wordtoSpan: Spannable = SpannableStringBuilder("사용중인 OTT 를\n선택해 주세요.")

        wordtoSpan.setSpan(ForegroundColorSpan(Color.parseColor("#00FF85")), 5, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.tvOttGuide.text = wordtoSpan

        viewModel.ottList.observe(this) {
            adapter.submitList(it)
        }

        viewModel.nextBtnState.observe(this) {
            binding.btnComplete.text = if(it) "다음" else "사용중이 아니에요."
        }
    }
}