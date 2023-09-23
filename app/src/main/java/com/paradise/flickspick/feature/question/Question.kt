package com.paradise.flickspick.feature.question

data class Question(
    val id: Int,
    val question: String,
    val answers: List<Answer>
) {
    data class Answer(
        val id: Int,
        val questionId: Int,
        val answer: String
    )
}