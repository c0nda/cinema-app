package com.android.cinemaapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "genre_id",
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = ["id"],
        childColumns = ["parentId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GenreDB(
    val id: Int,
    val name: String,
    var parentId: Int
)