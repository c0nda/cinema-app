package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Movie", indices = [Index(value = ["movieId"], unique = true)])
data class MovieDB(
    @PrimaryKey(autoGenerate = true)
    val number: Long = 0,
    val movieId: Int,
    val pgAge: Int,
    val title: String,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String?
)