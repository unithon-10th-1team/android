package com.paradise.flickspick.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SimpleMovie(
    val name: String = "매운새우깡",
    val image: String = "https://blog.kakaocdn.net/dn/bmIwxA/btrVE1Ql6YL/kfImMiXEd19Kch9ziopPj0/img.jpg",
    val starNum: Int = 3, // 3 / 5
    val description: String = "감독 빵빵이 | 각본 옥지 | 제작 빵빵이스튜디오",
)

data class HomeState(
    val isLoading: Boolean = false,
    val nickname: String = "옥지얌빵빵",
    val typename: String = "방구석 액션전문가",
    val tag: List<String> = listOf("화끈함", "섹시함", "장석연"),
    val similarRecommends: List<SimpleMovie> = (0..3).map { SimpleMovie() },
    val oppositeRecommends: List<SimpleMovie> = (0..3).map { SimpleMovie() },
)

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
}
