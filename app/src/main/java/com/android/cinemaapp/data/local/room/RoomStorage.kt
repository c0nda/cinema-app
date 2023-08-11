package com.android.cinemaapp.data.local.room

import com.android.cinemaapp.data.local.LocalDataSource
import com.android.cinemaapp.data.local.room.entities.ActorDB
import com.android.cinemaapp.data.local.room.entities.GenreDB
import com.android.cinemaapp.data.local.room.entities.MovieDB
import com.android.cinemaapp.data.local.room.entities.MovieDetailsDB
import com.android.cinemaapp.data.local.room.entities.MovieGenreCrossRef
import com.android.cinemaapp.model.Actor
import com.android.cinemaapp.model.Genre
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

class RoomStorage(private val db: MovieRoomDatabase) : LocalDataSource {
    override suspend fun loadMovies(): List<Movie> {
        return db.getMoviesDao().getMovies().map { movie ->
            Movie(
                id = movie.movie.movieId,
                pgAge = movie.movie.pgAge,
                title = movie.movie.title,
                genres = movie.genres.map { genre ->
                    Genre(genre.genreId, genre.name)
                },
                runningTime = movie.movie.runningTime,
                reviewCount = movie.movie.reviewCount,
                isLiked = movie.movie.isLiked,
                rating = movie.movie.rating,
                imageUrl = movie.movie.imageUrl
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails? {
        val movieDetails = db.getMovieDetailsDao().getMovieDetails(movieId) ?: return null
        return MovieDetails(
            id = movieDetails.details.movieDetailsId,
            pgAge = movieDetails.details.pgAge,
            title = movieDetails.details.title,
            genres = movieDetails.genres.map { genre ->
                Genre(genre.genreId, genre.name)
            },
            runtime = movieDetails.details.runtime,
            reviewCount = movieDetails.details.reviewCount,
            isLiked = movieDetails.details.isLiked,
            rating = movieDetails.details.rating,
            detailImageUrl = movieDetails.details.detailImageUrl,
            storyLine = movieDetails.details.storyLine,
            actors = movieDetails.actors.map { actor ->
                Actor(
                    id = actor.actorId,
                    name = actor.name,
                    imageUrl = actor.imageUrl
                )
            }
        )
    }

    override fun insertMovies(movies: List<Movie>) {
        val moviesDB = movies.map { movie ->
            MovieDB(
                movieId = movie.id,
                pgAge = movie.pgAge,
                title = movie.title,
                runningTime = movie.runningTime,
                reviewCount = movie.reviewCount,
                isLiked = movie.isLiked,
                rating = movie.rating,
                imageUrl = movie.imageUrl
            )
        }

        val genresDB = movies.map { movie ->
            movie.genres.map { genre ->
                GenreDB(
                    genreId = genre.id,
                    name = genre.name
                )
            }
        }

        val movieWithGenres = moviesDB zip genresDB

        movieWithGenres.forEach { pair ->
            pair.second.forEach {
                db.getMovieGenreCrossRefDao().insertMovieGenreCrossRef(
                    MovieGenreCrossRef(pair.first.movieId, it.genreId)
                )
                db.getGenreDao().insertGenre(it)
            }
        }

        db.getMoviesDao().insertMovies(moviesDB)
    }

    override fun insertMovieDetails(details: MovieDetails) {
        val movieDetailsDB = MovieDetailsDB(
            movieDetailsId = details.id,
            pgAge = details.pgAge,
            title = details.title,
            reviewCount = details.reviewCount,
            isLiked = details.isLiked,
            rating = details.rating,
            runtime = details.runtime,
            detailImageUrl = details.detailImageUrl,
            storyLine = details.storyLine
        )

        val genresDB = details.genres.map { genre ->
            GenreDB(
                genreId = genre.id,
                name = genre.name
            )
        }

        val actorsDB = details.actors.map { actor ->
            ActorDB(
                actorId = actor.id,
                name = actor.name,
                imageUrl = actor.imageUrl
            )
        }

        db.getMovieDetailsDao().insertMovieDetails(movieDetailsDB, genresDB, actorsDB)
    }
}
