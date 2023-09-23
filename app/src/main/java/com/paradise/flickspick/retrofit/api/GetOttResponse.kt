package com.paradise.flickspick.retrofit.api

import com.google.gson.annotations.SerializedName

data class GetOttResponse(
    @SerializedName("data") val data: OttsResponse
)

data class OttsResponse(
    @SerializedName("otts") val otts: List<Ott>
)

data class Ott(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("nameEng") val nameEng: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: String,
)