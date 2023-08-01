package com.android.cinemaapp.presentation.features.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.cinemaapp.repository.MovieRepository

class MoviesDetailsViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MoviesDetailsViewModel(repository) as T

}