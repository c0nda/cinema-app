package com.android.cinemaapp.repository

import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

interface MovieRepository {

    suspend fun fetchConfiguration()

    suspend fun loadMovies(): List<Movie>

    suspend fun searchMovies(name: String): List<Movie>

    suspend fun loadMovie(movieId: Int): MovieDetails
}