package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Actors",
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["id"],
        childColumns = ["parentId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ActorDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val parentId: Int
)