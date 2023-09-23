package com.paradise.flickspick.retrofit.model.response

import com.google.gson.annotations.SerializedName

data class GetOttResponse(
    @SerializedName("data") val data: OttsResponse
)

data class OttsResponse(
    @SerializedName("otts") val otts: List<Ott>
)
