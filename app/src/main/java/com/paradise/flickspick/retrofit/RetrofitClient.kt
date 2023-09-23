package com.paradise.flickspick.retrofit

import android.app.Application
import android.content.Context
import com.paradise.flickspick.data.TokenManager
import com.paradise.flickspick.retrofit.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {
    private const val BASE_URL = "http://3.39.135.242:8080/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
            //.create(AwesomeService::class.java)

        retrofit.create(ApiService::class.java)
    }
}



