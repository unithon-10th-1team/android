package com.paradise.flickspick.retrofit.api

import com.paradise.flickspick.feature.question.QuestionsResponse
import com.paradise.flickspick.retrofit.model.OttRequest
import com.paradise.flickspick.retrofit.model.ResultRequest
import com.paradise.flickspick.retrofit.model.response.GetOttResponse
import com.paradise.flickspick.retrofit.model.response.HomeResponse
import com.paradise.flickspick.retrofit.model.response.LoginUserData
import com.paradise.flickspick.retrofit.model.response.MyPageResponse
import com.paradise.flickspick.retrofit.model.response.RefreshResponse
import com.paradise.flickspick.retrofit.model.response.RegisterUserData
import com.paradise.flickspick.retrofit.model.response.ResultResponse
import com.paradise.flickspick.retrofit.model.response.ShareResponse
import com.paradise.flickspick.retrofit.response.UserAccountRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/v1/users")
    suspend fun registerUser(@Body user: RegisterUserData)

    @POST("api/v1/auth/login")
    suspend fun login(@Body user: LoginUserData): UserAccountRes

    @GET("api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
    )

    @GET("api/v1/ott")
    suspend fun getOtt(): GetOttResponse

    @POST("api/v1/ott")
    suspend fun postOtt(
        @Body request: OttRequest
    ): Response<Unit>


    @POST("api/v1/rec/query")
    suspend fun getRec(
        @Body result: ResultRequest
    ): ResultResponse

    @GET("api/v1/home")
    suspend fun getHome(): HomeResponse

    @GET("api/v1/me")
    suspend fun getMyPage(): MyPageResponse

    @GET("api/v1/questions")
    suspend fun getQuestionList(): QuestionsResponse

    @GET("api/v1/share")
    suspend fun getShare(): ShareResponse

    @GET("api/v1/movies/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int,
    ): RefreshResponse
}
