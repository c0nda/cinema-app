package com.android.cinemaapp.di

import com.android.cinemaapp.data.remote.retrofit.TheMovieDBApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

class NetworkModule {

    private val baseUrl = "https://api.themoviedb.org/3/"

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(json.asConverterFactory(contentType))

    private val retrofit = retrofitBuilder.build()

    val theMovieDBApi: TheMovieDBApi = retrofit.create()

    class ApiKeyInterceptor : Interceptor {

        companion object {
            private const val API_KEY = "90ac2e50812524d5b5e249555bd43518"
        }

        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            val urlBuilder = original.url.newBuilder()
            val url = urlBuilder
                .addQueryParameter("api_key", API_KEY)
                .build()

            val requestBuilder = original.newBuilder().url(url)

            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}