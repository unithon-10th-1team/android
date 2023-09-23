package com.paradise.flickspick.retrofit.model.response

data class RecType(
    val id: Int,
    val imageUrl: String,
    val tags: List<String>,
    val type: String
)
