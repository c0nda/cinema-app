package com.android.cinemaapp.presentation.features.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cinemaapp.model.MovieDetails
import com.android.cinemaapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails?>(null)
    val movieDetails: LiveData<MovieDetails?> = _movieDetails

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = repository.loadMovie(movieId)
        }
    }
}