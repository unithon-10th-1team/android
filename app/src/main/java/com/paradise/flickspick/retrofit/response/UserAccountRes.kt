package com.paradise.flickspick.retrofit.response
data class UserAccountRes(
    val data: UserData
)

data class UserData(
    val nickname: String,
    val token: String
)