package com.android.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(), View.OnClickListener {

    interface OnAvengersCardClickListener {
        fun onAvengersCardClick()
    }

    private val moviesList = arrayListOf(
        Movie(
            "Avengers: End Game",
            R.drawable.avengers_movie_xxxhdpi,
            arrayListOf("Action, Adventure, Fantasy"),
            4,
            125,
            137,
            false,
            13
        ),
        Movie(
            "Tenet",
            R.drawable.tenet_movie_xxxhdpi,
            arrayListOf("Action, Sci-Fi, Thriller"),
            5,
            98,
            97,
            true,
            16
        ),
        Movie(
            "Black Widow",
            R.drawable.black_widow_movie_xxxhdpi,
            arrayListOf("Action, Adventure, Sci-Fi"),
            4,
            38,
            102,
            false,
            13
        ),
        Movie(
            "Wonder Woman 1984",
            R.drawable.wonder_woman_movie_xxxhdpi,
            arrayListOf("Action, Adventure, Fantasy"),
            5,
            74,
            120,
            false,
            13
        ),
        Movie(
            "Avengers: End Game",
            R.drawable.avengers_movie_xxxhdpi,
            arrayListOf("Action, Adventure, Fantasy"),
            4,
            125,
            137,
            false,
            13
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

//        val fragmentAvengers = view.findViewById<ConstraintLayout>(R.id.fragment_movies)
//        fragmentAvengers.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val listener = activity as OnAvengersCardClickListener?
        listener?.onAvengersCardClick()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.rv_movies_list)
        val adapter = MovieAdapter(this.context, moviesList)
        list.adapter = adapter
        list.layoutManager = GridLayoutManager(this.context, 2)
    }
}