package com.paradise.flickspick.retrofit.model.response

data class MyPageResponse(
    val data: MyPageDataResponse,

)

data class MyPageDataResponse(
    val user: User,
    val otts: List<Ott>,
    val recType: List<RecType>,
    val tags: List<String>,
    val similarMovies: List<Movie>,
)
