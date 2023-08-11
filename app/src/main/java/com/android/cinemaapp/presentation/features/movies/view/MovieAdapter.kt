package com.android.cinemaapp.presentation.features.movies.view

import android.content.res.ColorStateList
import coil.load
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.cinemaapp.R
import com.android.cinemaapp.model.Movie

class MovieAdapter(private val onClickCard: (item: Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImage: ImageView = itemView.findViewById(R.id.iv_movie_image)
        private val movieName: TextView = itemView.findViewById(R.id.tv_movie_name)
        private val likeImage: ImageView = itemView.findViewById(R.id.iv_like)
        private val pgText: TextView = itemView.findViewById(R.id.tv_age_limit)
        private val genresText: TextView = itemView.findViewById(R.id.tv_genres)
        private val reviewsText: TextView = itemView.findViewById(R.id.tv_reviews)
        private val starsImages: List<ImageView> = listOf(
            itemView.findViewById(R.id.iv_first_star),
            itemView.findViewById(R.id.iv_second_star),
            itemView.findViewById(R.id.iv_third_star),
            itemView.findViewById(R.id.iv_forth_star),
            itemView.findViewById(R.id.iv_fifth_star)
        )

        fun bind(movie: Movie, onClickCard: (item: Movie) -> Unit) {
            movieImage.load(movie.imageUrl)
            movieName.text = movie.title
            genresText.text = movie.genres.joinToString { it.name }

            val context = itemView.context
            pgText.text = context.getString(R.string.movies_list_age_template, movie.pgAge)
            reviewsText.text =
                context.getString(R.string.movies_list_reviews_template, movie.reviewCount)
            val likeColor = if (movie.isLiked) {
                R.color.pink_like
            } else {
                R.color.white
            }

            ImageViewCompat.setImageTintList(
                likeImage,
                ColorStateList.valueOf(ContextCompat.getColor(likeImage.context, likeColor))
            )

            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (movie.rating / 2 > index) {
                    R.color.pink_like
                } else {
                    R.color.white
                }
                ImageViewCompat.setImageTintList(
                    imageView,
                    ColorStateList.valueOf(ContextCompat.getColor(imageView.context, colorId))
                )
            }

            itemView.setOnClickListener {
                onClickCard(movie)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}