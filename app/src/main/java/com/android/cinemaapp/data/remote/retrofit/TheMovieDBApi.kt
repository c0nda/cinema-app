package com.android.cinemaapp.data.remote.retrofit

import com.android.cinemaapp.data.remote.retrofit.response.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBApi {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailsResponse

    @GET("movie/popular")
    suspend fun getMoviesList(): PopularResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movieId: Int): MovieCastResponse
}