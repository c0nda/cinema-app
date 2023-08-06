package com.android.cinemaapp.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Relation

class MovieWithGenres(
    @Embedded
    val movie: MovieDB,
    @Relation(parentColumn = "id", entityColumn = "parentId")
    val genres: List<GenreDB>
)