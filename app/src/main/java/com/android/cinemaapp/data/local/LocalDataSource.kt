package com.android.cinemaapp.data.local

import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

interface LocalDataSource {

    suspend fun loadMovies(): List<Movie>

    suspend fun loadMovie(movieId: Int): Movie

    fun insertMovies(movies: List<Movie>)

    fun insertMovieDetails(details: MovieDetails)
}