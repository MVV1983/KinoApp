package com.example.kinoapp.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.model.datamodel.Film
import com.example.kinoapp.model.datamodel.ListItem
import com.example.kinoapp.adapters.holders.FilmHolder
import com.example.kinoapp.adapters.holders.GenresHolder
import com.example.kinoapp.adapters.holders.HeaderHolder

class ItemFilmAdapter(private val context: Context, val clickListener: ItemClickInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = mutableListOf<ListItem>()

    private companion object {
        const val HEADER = 1003
        const val GENRES = 1001
        const val FILM = 1002
    }

    fun update(data: List<ListItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderHolder(parent, context)
            GENRES -> GenresHolder(parent, context)
            FILM -> FilmHolder(parent, context)
            else -> throw IllegalArgumentException("Invalid type of data ")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is HeaderHolder -> holder.bind(list[position])
            is GenresHolder -> holder.bind(list[position], clickListener)
            is FilmHolder -> holder.bind(list[position], context, clickListener)
            else -> throw IllegalArgumentException("Invalid type of data ")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is ListItem.HeaderModel -> HEADER
            is ListItem.GenresModel -> GENRES
            is ListItem.FilmModel -> FILM
        }
    }

    interface ItemClickInterface {
        fun onClicked(film: Film)
        fun onClickGenres(genres: String)
    }
}