package com.example.movies.model

import com.example.movies.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object APIFactory {

    private val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", Utils.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(request)
        }
    }

    private fun loggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor())
        .build()

    val api: API by lazy {
        retrofit().create(API::class.java)
    }

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}