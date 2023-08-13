package com.android.cinemaapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.cinemaapp.data.local.room.dao.ActorDao
import com.android.cinemaapp.data.local.room.dao.GenreDao
import com.android.cinemaapp.data.local.room.dao.MovieDetailsActorCrossRefDao
import com.android.cinemaapp.data.local.room.dao.MovieDetailsDao
import com.android.cinemaapp.data.local.room.dao.MovieDetailsGenreCrossRefDao
import com.android.cinemaapp.data.local.room.dao.MovieGenreCrossRefDao
import com.android.cinemaapp.data.local.room.dao.MoviesDao
import com.android.cinemaapp.data.local.room.entities.*

@Database(
    entities = [
        MovieDB::class,
        MovieDetailsDB::class,
        ActorDB::class,
        GenreDB::class,
        MovieDetailsActorCrossRef::class,
        MovieDetailsGenreCrossRef::class,
        MovieGenreCrossRef::class
    ],
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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getMoviesDao(): MoviesDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao
    abstract fun getGenreDao(): GenreDao
    abstract fun getMovieGenreCrossRefDao(): MovieGenreCrossRefDao
    abstract fun getActorDao(): ActorDao
    abstract fun getMovieDetailsActorCrossRefDao(): MovieDetailsActorCrossRefDao
    abstract fun getMovieDetailsGenreCrossRefDao(): MovieDetailsGenreCrossRefDao
}