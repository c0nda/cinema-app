package com.android.cinemaapp.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Relation

class MovieDetailsWithActorsAndGenres(
    @Embedded
    val details: MovieDetailsDB,
    @Relation(parentColumn = "parentId", entityColumn = "parentId")
    val genres: List<GenreDB>,
    @Relation(parentColumn = "parentId", entityColumn = "parentId")
    val actors: List<ActorDB>
)