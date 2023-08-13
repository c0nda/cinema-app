package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.cinemaapp.data.local.room.entities.MovieDetailsDB
import com.android.cinemaapp.data.local.room.entities.MovieDetailsWithActorsAndGenres

@Dao
interface MovieDetailsDao {

    @Transaction
    @Query("SELECT * FROM MovieDetails WHERE movieDetailsId = :movieId")
    fun getMovieDetails(movieId: Int): MovieDetailsWithActorsAndGenres?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(details: MovieDetailsDB)
}