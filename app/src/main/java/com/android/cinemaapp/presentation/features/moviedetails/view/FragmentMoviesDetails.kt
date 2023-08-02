package com.android.cinemaapp.presentation.features.moviedetails.view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.cinemaapp.R
import com.android.cinemaapp.di.MovieRepositoryProvider
import com.android.cinemaapp.model.MovieDetails
import com.android.cinemaapp.presentation.features.moviedetails.viewmodel.MoviesDetailsViewModel
import com.android.cinemaapp.presentation.features.moviedetails.viewmodel.MoviesDetailsViewModelFactory

class FragmentMoviesDetails : Fragment() {

    private var listener: MovieDetailsBackClickListener? = null

    private val viewModel: MoviesDetailsViewModel by viewModels {
        MoviesDetailsViewModelFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return

        view.findViewById<RecyclerView>(R.id.rv_actors_list).apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this.adapter = ActorAdapter()
        }

        view.findViewById<View>(R.id.ll_button_back)?.setOnClickListener {
            listener?.onMovieDeselected()
        }

        viewModel.loadDetails(movieId)

        viewModel.movieDetails.observe(
            viewLifecycleOwner
        ) { movie -> movie?.let { bindUI(view, it) } }
    }

    private fun bindUI(view: View, movie: MovieDetails) {
        updateMovieDetailsInfo(movie)
        val adapter = view.findViewById<RecyclerView>(R.id.rv_actors_list).adapter as ActorAdapter
        adapter.submitList(movie.actors)
    }

    private fun updateMovieDetailsInfo(movie: MovieDetails) {
        view?.findViewById<ImageView>(R.id.iv_movie_details_image)
            ?.load(movie.detailImageUrl) { crossfade(true) }
        view?.findViewById<TextView>(R.id.film_name)?.text = movie.title
        view?.findViewById<TextView>(R.id.content_storyline)?.text = movie.storyLine
        view?.findViewById<TextView>(R.id.tv_tag)?.text = movie.genres.joinToString { it.name }
        view?.findViewById<TextView>(R.id.tv_movie_details_reviews)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.tv_age)?.text =
            context?.getString(R.string.movies_list_age_template, movie.pgAge)

        val starsImage = listOf<ImageView?>(
            view?.findViewById(R.id.iv_md_first_star),
            view?.findViewById(R.id.iv_md_second_star),
            view?.findViewById(R.id.iv_md_third_star),
            view?.findViewById(R.id.iv_md_forth_star),
            view?.findViewById(R.id.iv_md_fifth_star)
        )

        starsImage.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating / 2 > index) R.color.pink_like else R.color.gray_fragment
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun create(movieId: Int) = FragmentMoviesDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }
}