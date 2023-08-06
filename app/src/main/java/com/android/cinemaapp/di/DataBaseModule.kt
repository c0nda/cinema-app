package com.android.cinemaapp.di

import android.content.Context
import androidx.room.Room
import com.android.cinemaapp.data.local.room.MovieRoomDatabase

class DataBaseModule(context: Context) {

    val builder by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            MovieRoomDatabase::class.java,
            "room_database"
        ).fallbackToDestructiveMigration().build()
    }
}