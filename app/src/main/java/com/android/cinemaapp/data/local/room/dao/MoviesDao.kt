package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.cinemaapp.data.local.room.entities.MovieDB
import com.android.cinemaapp.data.local.room.entities.MovieWithGenres

@Dao
abstract class MoviesDao {

    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<MovieDB>)

}