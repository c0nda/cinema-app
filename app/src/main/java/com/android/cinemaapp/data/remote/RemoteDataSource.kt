package com.android.cinemaapp.data.remote

import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

interface RemoteDataSource {

    suspend fun loadMovies(): List<Movie>

    suspend fun searchMovies(name: String): List<Movie>

    suspend fun loadMovie(movieId: Int): MovieDetails
}