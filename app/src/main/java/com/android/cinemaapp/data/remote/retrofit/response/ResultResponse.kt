package com.android.cinemaapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("overview") val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)