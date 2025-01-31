package com.paradise.flickspick.feature.main.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradise.flickspick.retrofit.api.ApiService
import com.paradise.flickspick.retrofit.model.OttRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectOttViewModel @Inject constructor(
    val service: ApiService
): ViewModel() {

    private val _ottList = MutableLiveData<List<OttModel>>()
    val ottList: LiveData<List<OttModel>> = _ottList

    private val _nextBtnState = MutableLiveData(false)
    val nextBtnState: LiveData<Boolean> = _nextBtnState

    init {
        getOtts()
    }

    private fun getOtts() = viewModelScope.launch {
        val response = service.getOtt().data.otts.map { it.toModel() }
        _ottList.value = response
    }

    fun updateOttList(item: OttModel) = viewModelScope.launch {
        val newList = ottList.value?.map { if(it == item) it.copy(isSelected = item.isSelected.not()) else it } ?: return@launch
        _ottList.value = newList
    }

    fun updateNextBtnState() {
        val value = ottList.value?.find { it.isSelected }
        _nextBtnState.value = value != null
    }

    fun postOtt(ids: IntArray) = viewModelScope.launch {
        service.postOtt(request = OttRequest(ids = ids))
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