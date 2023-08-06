package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "MovieDetails",
    primaryKeys = ["parentId"],
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["id"],
        childColumns = ["parentId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class MovieDetailsDB(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val runtime: Int,
    val detailImageUrl: String,
    val storyLine: String,
    val parentId: Int
)