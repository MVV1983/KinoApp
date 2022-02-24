package com.example.kinoapp.interfaces

import com.example.kinoapp.model.datamodel.Film
import com.example.kinoapp.model.datamodel.ListItem
import dagger.Module
import dagger.Provides

interface Contract {

    interface Model {
        fun requestApi(presenter: Presenter)
    }

    interface View {
        fun showData(film: List<Film>)
        fun showError(message: String)
        fun updateAdapter(list: List<ListItem>)
    }

    interface Presenter {
        fun callApi()
        fun getData(list: List<Film>)
        fun catchError(message: String)
        fun decryptionCode(code: Int)
        fun getDataForAdapter()
        fun sendSelected(genres: String,state: Boolean)
        fun setView(view: View)
    }
}
