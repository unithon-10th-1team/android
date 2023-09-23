package com.paradise.flickspick.retrofit

import android.util.Log
import com.paradise.flickspick.core.PickApplication
import com.paradise.flickspick.core.TokenManager
import com.paradise.flickspick.retrofit.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://3.39.135.242:8080/"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // 요청/응답 본문을 포함한 모든 정보를 로깅합니다.
    }

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                    .addInterceptor(BearerTokenInterceptor(PickApplication().getTokenManager()))
//                    .addInterceptor(loggingInterceptor)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .build()
//            )
            .build()

        retrofit.create(ApiService::class.java)
    }
}

//class BearerTokenInterceptor(private val tokenManager: TokenManager) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//        val token = tokenManager.getToken()
//        return if (token != null) {
//            val originalRequest = chain.request()
//            val newRequest = originalRequest.newBuilder()
//                .header("Authorization", "Bearer $token")
//                .build()
//            chain.proceed(newRequest)
//        } else {
//            chain.proceed(chain.request())
//        }
//    }
//}



