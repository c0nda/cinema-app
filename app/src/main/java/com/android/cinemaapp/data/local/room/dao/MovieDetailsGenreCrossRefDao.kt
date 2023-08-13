package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.cinemaapp.data.local.room.entities.MovieDetailsGenreCrossRef

@Dao
interface MovieDetailsGenreCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieDetailsGenreCrossRef(crossRef: MovieDetailsGenreCrossRef)

}