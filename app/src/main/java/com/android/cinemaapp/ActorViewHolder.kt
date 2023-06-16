package com.android.cinemaapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)
    private val image: ImageView = itemView.findViewById(R.id.iv_actor_image)

    fun bind(actor: Actor) {
        name.text = actor.name
        image.setImageResource(actor.image)
    }
}