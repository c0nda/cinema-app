package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.cinemaapp.data.local.room.entities.MovieGenreCrossRef

@Dao
interface MovieGenreCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieGenreCrossRef(crossRef: MovieGenreCrossRef)

}