package com.example.kinoapp.model.datamodel

import java.io.Serializable

sealed class ListItem:Serializable {
    class HeaderModel(val name: String): ListItem()
    class GenresModel(val genres: Genres) : ListItem()
    class FilmModel(val film: Film) : ListItem()
}