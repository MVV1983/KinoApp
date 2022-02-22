package com.example.kinoapp.adapters.holders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.model.datamodel.ListItem
import kotlinx.android.synthetic.main.row_header.view.*

class HeaderHolder(parent: ViewGroup, context: Context) : RecyclerView.ViewHolder(
LayoutInflater.from(context).inflate(R.layout.row_header, parent, false)
) {
    var name: TextView? = itemView.name_header

    fun bind(item: ListItem) {
        val pos = item as ListItem.HeaderModel
        name?.text = pos.name
    }
}