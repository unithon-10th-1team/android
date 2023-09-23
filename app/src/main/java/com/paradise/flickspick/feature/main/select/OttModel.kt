package com.paradise.flickspick.feature.main.select

import com.paradise.flickspick.retrofit.api.Ott

data class OttModel(
    val id: Int,
    val name: String,
    val url: String,
    val isSelected: Boolean
)

fun Ott.toModel() =
    OttModel(
        id = id,
        name = name,
        url = imageUrl,
        isSelected = false
    )