package com.android.cinemaapp.repository

import com.android.cinemaapp.data.local.LocalDataSource
import com.android.cinemaapp.data.remote.RemoteDataSource
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun searchMovies(name: String): List<Movie> {
        return remoteDataSource.searchMovies(name)
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return withContext(Dispatchers.IO) {
            val movieDB = localDataSource.loadMovie(movieId)
            if (movieDB == null) {
                val movieFromNetwork = remoteDataSource.loadMovie(movieId)
                localDataSource.insertMovieDetails(movieFromNetwork)
                movieFromNetwork
            } else {
                movieDB
            }
        }
    }

    override suspend fun loadMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val moviesDB = localDataSource.loadMovies()
            if (moviesDB.isEmpty()) {
                val moviesFromNetwork = remoteDataSource.loadMovies()
                localDataSource.insertMovies(moviesFromNetwork)
                moviesFromNetwork
            } else {
                moviesDB
            }
        }
    }
}