package com.android.cinemaapp.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded
    val movie: MovieDB,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<GenreDB>
)