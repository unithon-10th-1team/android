package com.paradise.flickspick.retrofit

import android.content.Context
import com.paradise.flickspick.core.TokenManager
import com.paradise.flickspick.retrofit.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://3.39.135.242:8080/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        bearerTokenInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(bearerTokenInterceptor)
        .build()
//        .readTimeout(10L, TimeUnit.SECONDS)
//        .writeTimeout(10L, TimeUnit.SECONDS)
//        .connectTimeout(10L, TimeUnit.SECONDS)
//        .build()


    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideBearerTokenInterceptor(
        @ApplicationContext context: Context,
    ) = Interceptor { chain ->
        val token = TokenManager(context).getToken()
        if (token != null) {
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("X-FP-AUTH-TOKEN", token)
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(chain.request())
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

}