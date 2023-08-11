package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieDetailsId", "genreId"])
data class MovieDetailsGenreCrossRef(
    val movieDetailsId: Int,
    val genreId: Int
)