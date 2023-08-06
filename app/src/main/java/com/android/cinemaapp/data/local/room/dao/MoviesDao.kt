package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.cinemaapp.data.local.room.entities.MovieDB
import com.android.cinemaapp.data.local.room.entities.MovieWithGenres

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movie")
    fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieDB>)
}