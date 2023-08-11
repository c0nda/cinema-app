package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "MovieDetails",
    primaryKeys = ["movieDetailsId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDB::class,
            parentColumns = ["movieId"],
            childColumns = ["movieDetailsId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class MovieDetailsDB(
    val movieDetailsId: Int,
    val pgAge: Int,
    val title: String,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val runtime: Int,
    val detailImageUrl: String,
    val storyLine: String
)