package com.android.cinemaapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = itemView.findViewById(R.id.tv_movie_name)
    private val image: ImageView = itemView.findViewById(R.id.iv_movie_image)
    private val genre: TextView = itemView.findViewById(R.id.tv_tag)

    fun bind(movie: Movie) {
        name.text = movie.name
        image.setImageResource(movie.image)
        var genresList = ""
        for (item in movie.genre) {
            genresList += "$item, "
        }
        genresList.replaceFirst(".$".toRegex(), "")
        genresList.replaceFirst(".$".toRegex(), "")
        genre.text = genresList
    }
}