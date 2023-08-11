package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieDetailsId", "actorId"])
data class MovieDetailsActorCrossRef(
    val movieDetailsId: Int,
    val actorId: Int
)
