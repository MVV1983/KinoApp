package com.example.kinoapp.adapters.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.adapters.ItemFilmAdapter
import com.example.kinoapp.model.datamodel.ListItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*

class FilmHolder(parent: ViewGroup, context: Context) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
) {

    var name: TextView = itemView.localized_name
    var image: ImageView = itemView.card_Image
    var error: TextView = itemView.error_text_message

    fun bind(item: ListItem, context: Context, clickListener: ItemFilmAdapter.ItemClickInterface) {
        val pos = item as ListItem.FilmModel

        name.text = pos.film.localized_name

        if (pos.film.image_url == null) {
            error.visibility = View.VISIBLE
            image.setBackgroundResource(R.drawable.error_load_image)
        }

        Picasso.with(context).load(pos.film.image_url).into(image, object : Callback {
            override fun onSuccess() {
                Picasso.with(context).load(pos.film.image_url).into(image)
            }

            override fun onError() {
                error.visibility = View.VISIBLE
                image.setBackgroundResource(R.drawable.error_load_image)
                pos.film.image_url = null
            }
        })

        itemView.setOnClickListener {
            clickListener.onClicked(pos.film)
        }
    }
}