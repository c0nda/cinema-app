package com.android.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment(), View.OnClickListener {

    interface OnAvengersCardClickListener {
        fun onAvengersCardClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val fragmentAvengers = view.findViewById<ConstraintLayout>(R.id.fragment_avengers)
        fragmentAvengers.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val listener = activity as OnAvengersCardClickListener
        listener.onAvengersCardClick()
    }
}