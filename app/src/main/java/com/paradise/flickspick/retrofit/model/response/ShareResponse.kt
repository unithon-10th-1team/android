package com.paradise.flickspick.retrofit.model.response


data class ShareResponse(
    val data: ShareDataResponse
)

data class ShareDataResponse(
    val similarMovies: List<Movie>
)