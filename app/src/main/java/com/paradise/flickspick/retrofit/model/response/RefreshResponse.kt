package com.paradise.flickspick.retrofit.model.response

data class RefreshResponse(
    val data: RefreshDataResponse,
)

data class RefreshDataResponse(
    val movie: Movie,
    val recMovies: List<Movie>
)