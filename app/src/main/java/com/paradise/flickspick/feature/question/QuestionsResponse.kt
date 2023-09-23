package com.paradise.flickspick.feature.question

data class QuestionsResponse(
    val data: Questions
) {
    data class Questions(
        val questions: List<Question>
    )
}