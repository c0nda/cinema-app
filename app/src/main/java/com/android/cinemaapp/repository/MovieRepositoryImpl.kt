package com.android.cinemaapp.repository

import com.android.cinemaapp.data.local.LocalDataSource
import com.android.cinemaapp.data.remote.RemoteDataSource
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun fetchConfiguration() {
        remoteDataSource.fetchConfiguration()
    }

    override suspend fun loadMovies(): List<Movie> {
        return remoteDataSource.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return remoteDataSource.loadMovie(movieId)
    }
}