package com.android.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesDetails: Fragment() {

    private val actorsList = arrayListOf(
        Actor(
            "Robert Downey Jr.",
            R.drawable.robert_downey_jr_xxxhdpi
        ),
        Actor(
            "Chris Evans",
            R.drawable.chris_evans_xxxhdpi
        ),
        Actor(
            "Mark Ruffalo",
            R.drawable.mark_ruffalo_xxxhdpi
        ),
        Actor(
            "Chris Hemsworth",
            R.drawable.chris_hemsworth_xxxhdpi
        )
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.rv_actors_list)
        val adapter = ActorAdapter(this.context, actorsList)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
    }
}