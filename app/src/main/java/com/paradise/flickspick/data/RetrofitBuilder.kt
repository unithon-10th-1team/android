package com.paradise.flickspick.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://book.interpark.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookService = retrofit.create(PickService::class.java)
}