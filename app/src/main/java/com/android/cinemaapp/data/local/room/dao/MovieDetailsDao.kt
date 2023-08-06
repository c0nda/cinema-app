package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.cinemaapp.data.local.room.entities.MovieDetailsDB
import com.android.cinemaapp.data.local.room.entities.MovieDetailsWithActorsAndGenres

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM MovieDetails")
    fun getMovieDetails(): MovieDetailsWithActorsAndGenres

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(details: MovieDetailsDB)

}