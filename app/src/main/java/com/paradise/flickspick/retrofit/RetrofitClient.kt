package com.paradise.flickspick.retrofit

import com.paradise.flickspick.retrofit.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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



