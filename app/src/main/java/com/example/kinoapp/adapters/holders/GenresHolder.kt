package com.example.kinoapp.adapters.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.adapters.ItemFilmAdapter
import com.example.kinoapp.model.datamodel.ListItem
import kotlinx.android.synthetic.main.item_genres.view.*

class GenresHolder(parent: ViewGroup, context: Context) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.item_genres, parent, false)
) {
    var name: Button = itemView.name_genres_button

    fun bind(item: ListItem, clickListener: ItemFilmAdapter.ItemClickInterface) {
        val pos = item as ListItem.GenresModel
        name.text = pos.genres.name
      
        if (pos.genres.isSelected) {
            name.setBackgroundResource(R.drawable.button_press)
            pos.genres.isSelected = true
        } else {
            name.setBackgroundResource(R.drawable.button_not_pressed)
            pos.genres.isSelected = false
        }

        itemView.name_genres_button.setOnClickListener {
            clickListener.onClickGenres(pos.genres.name,pos.genres.isSelected)
        }
    }
}
