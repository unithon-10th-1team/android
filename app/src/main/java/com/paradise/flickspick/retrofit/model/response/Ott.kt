package com.paradise.flickspick.retrofit.model.response

import com.google.gson.annotations.SerializedName

data class Ott(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("nameEng") val nameEng: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: String,
)