package com.android.cinemaapp

import android.app.Application
import com.android.cinemaapp.data.local.room.MovieRoomDatabase

class CinemaApp: Application() {

    companion object {
        lateinit var db: MovieRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = MovieRoomDatabase.getInstance(this)
    }
}