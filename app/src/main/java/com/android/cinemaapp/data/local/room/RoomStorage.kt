package com.android.cinemaapp.data.local.room

import com.android.cinemaapp.data.local.LocalDataSource
import com.android.cinemaapp.data.local.room.entities.GenreDB
import com.android.cinemaapp.data.local.room.entities.MovieDB
import com.android.cinemaapp.model.Actor
import com.android.cinemaapp.model.Genre
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

class RoomStorage(private val db: MovieRoomDatabase) : LocalDataSource {
    override suspend fun loadMovies(): List<Movie> {
        return db.getMoviesDao().getMovies().map { movie ->
            Movie(
                id = movie.movie.id,
                pgAge = movie.movie.pgAge,
                title = movie.movie.title,
                genres = movie.genres.map { genre ->
                    Genre(genre.id, genre.name)
                },
                runningTime = movie.movie.runningTime,
                reviewCount = movie.movie.reviewCount,
                isLiked = movie.movie.isLiked,
                rating = movie.movie.rating,
                imageUrl = movie.movie.imageUrl
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        val movieDetails = db.getMovieDetailsDao().getMovieDetails()
        return MovieDetails(
            id = movieDetails.details.id,
            pgAge = movieDetails.details.pgAge,
            title = movieDetails.details.title,
            genres = movieDetails.genres.map { genre ->
                Genre(genre.id, genre.name)
            },
            runtime = movieDetails.details.runtime,
            reviewCount = movieDetails.details.reviewCount,
            isLiked = movieDetails.details.isLiked,
            rating = movieDetails.details.rating,
            detailImageUrl = movieDetails.details.detailImageUrl,
            storyLine = movieDetails.details.storyLine,
            actors = movieDetails.actors.map { actor ->
                Actor(
                    id = actor.id,
                    name = actor.name,
                    imageUrl = actor.imageUrl
                )
            }
        )
    }

    override fun insertMovies(movies: List<Movie>) {
        val moviesDB = movies.map { movie ->
            MovieDB(
                id = movie.id,
                pgAge = movie.pgAge,
                title = movie.title,
                runningTime = movie.runningTime,
                reviewCount = movie.reviewCount,
                isLiked = movie.isLiked,
                rating = movie.rating,
                imageUrl = movie.imageUrl
            )
        }

        db.getMoviesDao().insertMovies(moviesDB)

        val genresDB = movies.map { movie -> movie.genres.map { genre ->
            GenreDB(
                id = genre.id,
                name = genre.name,
                parentId = -1
            )
        }}

        val moviesWithGenres = moviesDB zip genresDB

        moviesWithGenres.forEach {
            db.getGenresDao().insertGenresForMovie(it.first, it.second)
        }
    }

    override fun insertMovieDetails(details: MovieDetails) {
        TODO()
    }

}