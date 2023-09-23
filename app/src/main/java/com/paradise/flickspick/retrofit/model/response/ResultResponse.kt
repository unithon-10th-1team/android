package com.paradise.flickspick.retrofit.model.response

data class ResultResponse(
    val data: Data
)

data class Data(
    val movie: Movie,
    val recType: RecType,
    val recMovies: List<RecMovy>,
)
