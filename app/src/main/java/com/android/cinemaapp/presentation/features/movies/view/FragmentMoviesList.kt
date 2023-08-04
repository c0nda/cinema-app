package com.android.cinemaapp.presentation.features.movies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.cinemaapp.R
import com.android.cinemaapp.di.MovieRepositoryProvider
import com.android.cinemaapp.model.Movie
import com.android.cinemaapp.presentation.features.movies.viewmodel.MoviesViewModel
import com.android.cinemaapp.presentation.features.movies.viewmodel.MoviesViewModelFactory

class FragmentMoviesList : Fragment() {

    private var listener: MoviesListItemClickListener? = null

    private val viewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<androidx.appcompat.widget.SearchView>(R.id.sv_search_film)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchMovies(newText)
                }
                return true
            }
        })


        view.findViewById<RecyclerView>(R.id.rv_movies_list).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)
            val adapter = MovieAdapter { movie ->
                listener?.onMovieSelected(movie)
            }
            this.adapter = adapter
            loadDataToAdapter(adapter)
        }
    }

    private fun loadDataToAdapter(adapter: MovieAdapter) {
        viewModel.movies.observe(
            viewLifecycleOwner
        ) { moviesList -> adapter.submitList(moviesList) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }

    companion object {
        fun create() = FragmentMoviesList()
    }
}