package com.paradise.flickspick.retrofit.model

data class ResultRequest(
    val answers: List<Answer>,
    val ottIds: List<Int>
) {
    data class Answer(
        val answerId: Int,
        val questionId: Int
    )
}