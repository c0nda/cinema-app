package com.android.cinemaapp


data class Movie(
    val name: String,
    val image: Int,
    val genre: ArrayList<String>,
    val stars: Int,
    val reviews: Int,
    val time: Int,
    val isLiked: Boolean,
    val ageLimit: Int
)
