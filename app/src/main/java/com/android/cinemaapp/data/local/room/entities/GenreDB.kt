package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Genre")
data class GenreDB(
    @PrimaryKey
    val genreId: Int,
    val name: String
)