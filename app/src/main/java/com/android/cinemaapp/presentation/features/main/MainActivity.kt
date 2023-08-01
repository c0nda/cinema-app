package com.android.cinemaapp.presentation.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.cinemaapp.R
import com.android.cinemaapp.data.local.json.JsonStorage
import com.android.cinemaapp.data.remote.retrofit.RetrofitStorage
import com.android.cinemaapp.di.MovieRepositoryProvider
import com.android.cinemaapp.di.NetworkModule
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.presentation.features.moviedetails.view.FragmentMoviesDetails
import com.android.cinemaapp.presentation.features.movies.view.FragmentMoviesList
import com.android.cinemaapp.repository.MovieRepository
import com.android.cinemaapp.repository.MovieRepositoryImpl

class MainActivity : AppCompatActivity(),
    FragmentMoviesList.MoviesListItemClickListener,
    FragmentMoviesDetails.MovieDetailsBackClickListener,
    MovieRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitStorage(networkModule.theMovieDBApi)
    private val localDataSource = JsonStorage(this)
    private val movieRepository = MovieRepositoryImpl(localDataSource, remoteDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                FragmentMoviesList.create(),
                FragmentMoviesList::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesList::class.java.simpleName}")
            .commit()
    }

    private fun routeToMovieDetails(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                FragmentMoviesDetails.create(movieId),
                FragmentMoviesDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = movieRepository

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie.id)
    }
}