package com.paradise.flickspick.data

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PickService {

    @POST("/api/v1/users")
    suspend fun signUp(
        @Query("username") username: String,
        @Query("nickname") nickname: String,
        @Query("password") password: String
    )

    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
    )

    @POST("/api/v1/auth/login")
    suspend fun signIn(
        @Query("username") username: String,
        @Query("password") password: String
    )

    @GET("/api/v1/questions")
    suspend fun getQuestions()

    @GET("/api/v1/ott")
    suspend fun getOtt()
}