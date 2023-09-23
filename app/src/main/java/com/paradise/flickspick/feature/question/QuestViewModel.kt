package com.paradise.flickspick.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradise.flickspick.retrofit.api.ApiService
import com.paradise.flickspick.retrofit.model.ResultRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val service: ApiService
) : ViewModel() {

    private val _questionList = MutableLiveData<List<Question>>()
    val questionList: LiveData<List<Question>> = _questionList

    private val _viewState = MutableLiveData(0)
    val viewState: LiveData<Int> = _viewState

    private val _completeInfo = MutableLiveData<ResultRequest?>(null)
    val completeInfo: LiveData<ResultRequest?> = _completeInfo


    var answerList = MutableList(5) { 0 to 0 }
    var currentPage = 0
    var maxPageSize = 0
    var ottIds = arrayListOf<Int>()

    init {
        getQuestionList()
        getUserInfo()
    }

    private fun getQuestionList() {
        viewModelScope.launch {
            val response = service.getQuestionList()
            _questionList.value = response.data.questions
            maxPageSize = response.data.questions.size

        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val response = service.getMyPage()
            ottIds.addAll(List(response.data.otts.size) { response.data.otts[it].id })
        }
    }

    fun updateViewState(state: Int) {
        _viewState.value = state
    }

    fun updateCurrentPage(state: Int) {
        currentPage = state
    }

    fun updateAnswer(state: Int, questionId: Int, answerId: Int) {
        answerList[state] = questionId to answerId

        if (state == maxPageSize-1) {
            val resultRequest = ResultRequest(
                List(answerList.size) {
                    ResultRequest.Answer(questionId = answerList[it].first, answerId = answerList[it].second)
                },
                ottIds
            )
            _completeInfo.value = resultRequest
        }
    }
}