package com.paradise.flickspick.retrofit.model

import com.google.gson.annotations.SerializedName

data class OttRequest(
    @SerializedName("ids") val ids: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OttRequest

        if (!ids.contentEquals(other.ids)) return false

        return true
    }

    override fun hashCode(): Int {
        return ids.contentHashCode()
    }
}