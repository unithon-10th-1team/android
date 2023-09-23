package com.paradise.flickspick.feature.main.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectOttViewModel: ViewModel() {

    private val _ottList = MutableLiveData<List<OttModel>>()
    val ottList: LiveData<List<OttModel>> = _ottList

    private val _nextBtnState = MutableLiveData(false)
    val nextBtnState: LiveData<Boolean> = _nextBtnState

    init {
        _ottList.value = mockList
    }

    fun updateOttList(item: OttModel) {
        val newList = ottList.value?.map { if(it == item) it.copy(isSelected = item.isSelected.not()) else it } ?: return
        _ottList.value = newList
    }

    fun updateNextBtnState() {
        val value = ottList.value?.find { it.isSelected }
        _nextBtnState.value = value != null
    }

    companion object {
        val mockList = listOf(
            OttModel(0,"Tving", "", false),
            OttModel(1,"Netfilx", "", false),
            OttModel(2,"Wavve", "", false),
            OttModel(3,"쿠팡플레이", "", false),
            OttModel(4,"Watcha", "", false),
            OttModel(5,"Disne plus", "", false),
        )
    }
}