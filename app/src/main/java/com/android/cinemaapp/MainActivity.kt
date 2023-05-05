package com.android.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), FragmentMoviesList.OnAvengersCardClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_container, FragmentMoviesList())
                commit()
            }
        }
    }

    override fun onAvengersCardClick() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, FragmentMoviesDetails())
            addToBackStack(null)
            commit()
        }
    }
}