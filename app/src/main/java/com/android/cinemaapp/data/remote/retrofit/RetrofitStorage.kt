package com.android.cinemaapp.data.remote.retrofit

import com.android.cinemaapp.data.remote.RemoteDataSource
import com.android.cinemaapp.data.remote.retrofit.response.ImageResponse
import com.android.cinemaapp.model.Actor
import com.android.cinemaapp.model.Genre
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.model.MovieDetails

class RetrofitStorage(private val api: TheMovieDBApi) : RemoteDataSource {

    private var imageResponse: ImageResponse? = null
    private var baseUrl: String? = null
    private var posterSize: String? = null
    private var profileSize: String? = null
    private var backdropSize: String? = null

    override suspend fun fetchConfiguration() {
        val imagesResponse = api.getConfiguration().images
        baseUrl = imagesResponse.secureBaseUrl
        posterSize = imagesResponse.posterSizes.find { it == "w342" }
        backdropSize = imagesResponse.backdropSizes.find { it == "w780" }
        profileSize = imagesResponse.profileSizes.find { it == "w185" }
    }

    override suspend fun loadMovies(): List<Movie> {
        imageResponse = api.getConfiguration().images
        baseUrl = imageResponse?.secureBaseUrl
        profileSize = imageResponse?.profileSizes?.find { it == "w185" }
        backdropSize = imageResponse?.backdropSizes?.find { it == "w780" }
        posterSize = imageResponse?.posterSizes?.find { it == "w500" }

        return api.getMoviesList().results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                pgAge = if (movie.adult) 16 else 13,
                genres = api.getGenres().genres.map { genre ->
                    Genre(
                        id = genre.id,
                        name = genre.name
                    )
                },
                runningTime = 150,
                reviewCount = movie.voteCount,
                isLiked = false,
                rating = movie.voteAverage.toInt(),
                imageUrl = baseUrl.orEmpty()
                    .plus(posterSize.orEmpty())
                    .plus(movie.posterPath)
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        val details = api.getMovieDetails(movieId)
        return MovieDetails(
            id = details.id,
            pgAge = if (details.adult) 16 else 13,
            title = details.title,
            genres = details.genres.map { Genre(it.id, it.name) },
            reviewCount = details.voteCount,
            isLiked = false,
            rating = details.voteAverage.toInt(),
            detailImageUrl = baseUrl.orEmpty()
                .plus(backdropSize.orEmpty())
                .plus(details.backdropPath.orEmpty()),
            storyLine = details.overview,
            runtime = details.runtime ?: 150,
            actors = api.getMovieCast(movieId).casts.map { actor ->
                Actor(
                    id = actor.id,
                    name = actor.name,
                    imageUrl = baseUrl.orEmpty()
                        .plus(profileSize.orEmpty())
                        .plus(actor.profilePath.orEmpty())
                )
            }
        )
    }
}