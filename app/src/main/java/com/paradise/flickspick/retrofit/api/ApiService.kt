package com.paradise.flickspick.retrofit.api

import com.paradise.flickspick.feature.user.SignUpActivity
import com.paradise.flickspick.retrofit.model.LoginUserData
import com.paradise.flickspick.retrofit.model.RegisterUserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("api/v1/users")
    suspend fun registerUser(@Body user: RegisterUserData)

    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
    )

    @POST("/api/v1/auth/login")
    suspend fun signIn(@Body user: LoginUserData): Call<Void>

    @GET("/api/v1/questions")
    suspend fun getQuestions()

    @GET("/api/v1/ott")
    suspend fun getOtt()
}