package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.cinemaapp.data.local.room.entities.MovieDetailsActorCrossRef

@Dao
interface MovieDetailsActorCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieDetailsActorCrossRef(crossRef: MovieDetailsActorCrossRef)

}