package com.android.cinemaapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.cinemaapp.data.local.room.dao.MovieDetailsDao
import com.android.cinemaapp.data.local.room.dao.MoviesDao
import com.android.cinemaapp.data.local.room.entities.ActorDB
import com.android.cinemaapp.data.local.room.entities.GenreDB
import com.android.cinemaapp.data.local.room.entities.MovieDB
import com.android.cinemaapp.data.local.room.entities.MovieDetailsDB

@Database(
    entities = [MovieDB::class, MovieDetailsDB::class, ActorDB::class, GenreDB::class],
    version = 1,
    exportSchema = false
)
abstract class MovieRoomDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "MoviesDatabase.db"

        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getInstance(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getMoviesDao(): MoviesDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao
}