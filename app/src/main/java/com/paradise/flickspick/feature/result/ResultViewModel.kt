package com.paradise.flickspick.feature.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradise.flickspick.feature.home.SimpleMovie
import com.paradise.flickspick.retrofit.api.ApiService
import com.paradise.flickspick.retrofit.model.ResultRequest
import com.paradise.flickspick.retrofit.model.response.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Movie(
    val name: String = "매운새우깡",
    val image: String = "https://newsimg.hankookilbo.com/2019/05/08/201905082306085099_1.jpg",
    val starNum: Int = 3, // 3 / 5
    val description: String = "감독 빵빵이 | 각본 옥지 | 제작 빵빵이스튜디오",
    val tag: List<String> = listOf("화끈함", "섹시함", "장석연"),
    val reason: String = "남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다. 남자들의 영화입니다.",
    val plot: String = "최고의 파일럿이자 전설적인 인물 매버릭(톰 크루즈)은 자신이 졸업한 훈련학교 교관으로 발탁된다. 그의 명성을 모르던 팀원들은 매버릭의 지시를 무시하지만 실전을 방불케 하는 상공 훈련에서 눈으로 봐도 믿기 힘든 전설적인 조종 실력에 모두가 압도된다. 매버릭의 지휘아래 견고한 팀워크를 쌓아가던 팀원들에",
)

fun Movie.toSimpleMovie() = SimpleMovie(
    name = name,
    image = image,
    starNum = starNum,
    description = description,
)

data class ResultState(
    val nickname: String = "옥지얌빵빵",
    val typename: String = "방구석 액션전문가",
    val movies: Movie = Movie(),
    val tag: List<String> = listOf("화끈함", "섹시함", "장석연"),
    val recMovies: List<Movie> = (1..3).map { Movie() },

    val shareMovies: List<Movie> = emptyList()
)

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val service: ApiService
) : ViewModel() {

    var resultRequest: ResultRequest? = null

    init {
        getResult()
        getShare()
    }

    private fun getResult() = viewModelScope.launch {
        runCatching {
            service.getRec(
                result = ResultRequest( // TODO 정보 채우기
                    answers = resultRequest?.answers ?: return@launch ,
                    ottIds = resultRequest?.ottIds ?: return@launch
                )
            )
        }.onSuccess { result ->
            val response = result.data
            _state.value = _state.value.copy(
                nickname = "임시 닉네임", // TODO 닉네임 정보 누락
                typename = response.recType.type,
                movies = response.movie.toModel(response.recType.tags),
                tag = response.recType.tags,
                recMovies = response.recMovies.map { it.toModel() }
            )
        }.onFailure { exception ->

        }
    }

    fun updateQuestionRequest(question: ResultRequest) {
        resultRequest = question
    }

    private fun getShare() = viewModelScope.launch {
        val response = service.getShare().data

        _state.value = _state.value.copy(
            shareMovies = response.similarMovies.map { it.toModel() }
        )
    }

    private val _state = MutableStateFlow(ResultState())
    val state: StateFlow<ResultState> = _state.asStateFlow()
}