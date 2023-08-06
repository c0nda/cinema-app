package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.Relation

@Entity(tableName = "MovieDetails")
data class MovieDetailsDB(
    val id: Int,
    val pgAge: Int,
    val title: String,
    @Relation(parentColumn = "parentId", entityColumn = "parentId")
    val genres: List<GenreDB>,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val runtime: Int,
    val detailImageUrl: String,
    val storyLine: String,
    @Relation(parentColumn = "parentId", entityColumn = "parentId")
    val actors: List<ActorDB>,
    val parentId: Int
)