package com.android.cinemaapp.model

data class MovieDetails(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val runtime: Int,
    val detailImageUrl: String,
    val storyLine: String,
    val actors: List<Actor>
)