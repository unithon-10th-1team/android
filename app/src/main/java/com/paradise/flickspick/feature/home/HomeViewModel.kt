package com.paradise.flickspick.feature.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradise.flickspick.R
import com.paradise.flickspick.feature.result.toSimpleMovie
import com.paradise.flickspick.retrofit.api.ApiService
import com.paradise.flickspick.retrofit.model.response.Ott
import com.paradise.flickspick.retrofit.model.response.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.OffsetTime
import javax.inject.Inject

data class SimpleMovie(
    val name: String = "매운새우깡",
    val image: String = "https://blog.kakaocdn.net/dn/bmIwxA/btrVE1Ql6YL/kfImMiXEd19Kch9ziopPj0/img.jpg",
    val starNum: Int = 3, // 3 / 5
    val description: String = "감독 빵빵이 | 각본 옥지 | 제작 빵빵이스튜디오",
)

data class HomeState(
    val isLoading: Boolean = false,

    // for home
    val nickname: String = "옥지얌빵빵",
    val typename: String = "방구석 액션전문가",
    val tag: List<String> = listOf("화끈함", "섹시함", "장석연"),
    val similarRecommends: List<SimpleMovie> = (0..3).map { SimpleMovie() },
    val oppositeRecommends: List<SimpleMovie> = (0..3).map { SimpleMovie() },

    // for myPage
    val imageUrl: String = "https://blog.kakaocdn.net/dn/bmIwxA/btrVE1Ql6YL/kfImMiXEd19Kch9ziopPj0/img.jpg",
    val usingOtt: List<Ott> = emptyList(),
    val recommends: List<SimpleMovie> = (0..3).map { SimpleMovie() },

    )

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val client: ApiService
) : ViewModel() {

    init {
        getHome()
        getMyPage()
    }

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private fun getHome() = viewModelScope.launch {
        val response = client.getHome().data
        val recType = response.recType.first()

        _state.value = _state.value.copy(
            nickname = response.user.nickname,
            typename = recType.type,
            tag = recType.tags,
            similarRecommends = response.similarMovies.map { it.toModel().toSimpleMovie() },
            oppositeRecommends = response.differentMovies.map { it.toModel().toSimpleMovie() },
            imageUrl = recType.imageUrl,
        )
    }

    private fun getMyPage() = viewModelScope.launch {
        val response = client.getMyPage().data

        _state.value = _state.value.copy(
            usingOtt = response.otts,
            recommends = response.similarMovies.map { it.toModel().toSimpleMovie() }
        )
    }

}
