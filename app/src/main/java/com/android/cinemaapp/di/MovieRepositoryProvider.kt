package com.android.cinemaapp.di

import com.android.cinemaapp.repository.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}