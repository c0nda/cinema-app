package com.android.cinemaapp.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieDetailsWithActorsAndGenres(
    @Embedded
    val details: MovieDetailsDB,
    @Relation(
        parentColumn = "movieDetailsId",
        entityColumn = "genreId",
        associateBy = Junction(MovieDetailsGenreCrossRef::class)
    )
    val genres: List<GenreDB>,
    @Relation(
        parentColumn = "movieDetailsId",
        entityColumn = "actorId",
        associateBy = Junction(MovieDetailsActorCrossRef::class)
    )
    val actors: List<ActorDB>
)