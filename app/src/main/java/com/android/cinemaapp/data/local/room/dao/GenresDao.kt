package com.android.cinemaapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.cinemaapp.data.local.room.entities.GenreDB
import com.android.cinemaapp.data.local.room.entities.MovieDB

@Dao
abstract class GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGenres(genres: List<GenreDB>)

    fun insertGenresForMovie(movie: MovieDB, genres: List<GenreDB>) {
        genres.forEach {
            it.parentId = movie.id
        }

        insertGenres(genres)
    }
}