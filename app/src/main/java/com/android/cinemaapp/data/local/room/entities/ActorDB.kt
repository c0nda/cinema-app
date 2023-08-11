package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Actor")
data class ActorDB(
    @PrimaryKey
    val actorId: Int,
    val name: String,
    val imageUrl: String?
)