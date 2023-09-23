package com.paradise.flickspick.retrofit.model.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("data") val data: HomeDataResponse
)
data class HomeDataResponse(
    @SerializedName("user") val user: User,
    @SerializedName("recType") val recType: List<RecType>,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("similarMovies") val similarMovies: List<Movie>,
    @SerializedName("differentMovies") val differentMovies: List<Movie>,
)
