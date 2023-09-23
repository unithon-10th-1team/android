package com.paradise.flickspick.retrofit.model.response

data class RecMovy(
    val director: String,
    val grade: Int,
    val id: Int,
    val imageUrl: String,
    val people: String,
    val plot: String,
    val producer: String,
    val reason: String,
    val scenario: String,
    val title: String
)

data class Movie(
    val director: String,
    val grade: Int,
    val id: Int,
    val imageUrl: String,
    val people: String,
    val plot: String,
    val producer: String,
    val reason: String,
    val scenario: String,
    val title: String
)

fun Movie.toModel(tag: List<String> = emptyList()) = com.paradise.flickspick.feature.result.Movie(
    name = title,
    image = imageUrl,
    starNum = grade,
    description = "감독 ${director} | 프로듀서 ${producer} | 시나리오 ${scenario}",
    tag = tag,
    reason = reason,
    plot = plot
)

fun RecMovy.toModel(tag: List<String> = emptyList()) = com.paradise.flickspick.feature.result.Movie(
    name = title,
    image = imageUrl,
    starNum = grade,
    description = "감독 ${director} | 프로듀서 ${producer} | 시나리오 ${scenario}",
    tag = tag,
    reason = reason,
    plot = plot
)