package com.android.cinemaapp.data.local

import com.android.cinemaapp.model.MovieOld

interface LocalDataSource {

    suspend fun loadMovies(): List<MovieOld>

    suspend fun loadMovie(movieId: Int): MovieOld?
}