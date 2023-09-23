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
    val id: Int = 0,
    val name: String = "",
    val image: String = "https://newsimg.hankookilbo.com/2019/05/08/201905082306085099_1.jpg",
    val starNum: Int = 0,
    val description: String = "",
    val tag: List<String> = emptyList(),
    val reason: String = "",
    val plot: String = "",
)

fun Movie.toSimpleMovie(id: Int = 0) = SimpleMovie(
    name = name,
    image = image,
    starNum = starNum,
    description = description,
    id = id
)

data class ResultState(
    val nickname: String = "",
    val typename: String = "",
    val movies: Movie = Movie(),
    val tag: List<String> = emptyList(),
    val recMovies: List<Movie> = emptyList(),

    val shareMovies: List<Movie> = emptyList()
)

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val service: ApiService
) : ViewModel() {

    private var resultRequest: ResultRequest? = null

    init {
        getShare()
    }

    fun updateMovie(id: Int) = viewModelScope.launch {
        runCatching {
            service.getMovie(id)
        }.onSuccess {
            val response = it.data

            _state.value = _state.value.copy(
                movies = response.movie.toModel(id = response.movie.id),
                recMovies = response.recMovies.map { it.toModel(id = it.id) }
            )
        }
    }

    fun getResult(nickname: String) = viewModelScope.launch {
        runCatching {
            service.getRec(
                result = ResultRequest(
                    answers = resultRequest?.answers ?: return@launch ,
                    ottIds = resultRequest?.ottIds ?: return@launch
                )
            )
        }.onSuccess { result ->
            val response = result.data
            _state.value = _state.value.copy(
                nickname = nickname,
                typename = response.recType.type,
                movies = response.movie.toModel(response.recType.tags),
                tag = response.recType.tags,
                recMovies = response.recMovies.map { it.toModel(id = it.id) }
            )
        }.onFailure { exception ->
            exception
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

    private val _state = MutableStateFlow<ResultState>(ResultState())
    val state: StateFlow<ResultState> = _state.asStateFlow()
}