package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "Movie")
data class MovieDB(
    @PrimaryKey
    val id: Int,
    val pgAge: Int,
    val title: String,
    @Relation(parentColumn = "id", entityColumn = "parentId")
    val genres: List<GenreDB>,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String?,
)