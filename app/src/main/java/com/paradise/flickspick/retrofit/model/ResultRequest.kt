package com.paradise.flickspick.retrofit.model

import java.io.Serializable

data class ResultRequest(
    val answers: List<Answer>,
    val ottIds: List<Int>
): Serializable {
    data class Answer(
        val answerId: Int,
        val questionId: Int
    ): Serializable
}