package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieDB(
    @PrimaryKey
    val id: Int,
    val pgAge: Int,
    val title: String,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String?
)