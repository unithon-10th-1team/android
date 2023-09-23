package com.paradise.flickspick.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestViewModel : ViewModel() {

    private val _viewState = MutableLiveData(0)
    val viewState: LiveData<Int> = _viewState

    var currentPage = 0

    fun updateViewState(state: Int) {
        _viewState.value = state
    }

    fun updateCurrentPage(state: Int) {
        currentPage= state
    }
}