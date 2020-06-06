package com.example.movies.data

import com.example.movies.BuildConfig
import com.example.movies.Utils
import com.example.movies.Utils.API_KEY
import com.example.movies.Utils.API_KEY_QUERY_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiCreator {

    val api: MovieApi by lazy {
        retrofit()
            .create(MovieApi::class.java)
    }

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor())
        }
        return builder.build()
    }

    private val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter(API_KEY_QUERY_KEY, API_KEY)
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
}