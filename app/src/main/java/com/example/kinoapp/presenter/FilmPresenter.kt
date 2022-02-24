package com.example.kinoapp.presenter

import android.util.Log
import com.example.kinoapp.interfaces.Contract
import com.example.kinoapp.model.datamodel.Film
import com.example.kinoapp.model.datamodel.Genres
import com.example.kinoapp.model.datamodel.Header
import com.example.kinoapp.model.datamodel.ListItem
import com.example.kinoapp.repository.FilmRepository

class FilmPresenter() : Contract.Presenter {

    private lateinit var fView: Contract.View
    private var model: Contract.Model = FilmRepository()

    private var allFilms: List<Film> = mutableListOf()
    private var allGenres: List<Genres> = mutableListOf()
    private var allFilmGenres: List<Genres> = mutableListOf()
    private var selectedFilm: List<Film> = mutableListOf()
    private var mergeringList: List<ListItem> = mutableListOf()


    override fun callApi() {
        if (allFilms.isEmpty()) {
            model.requestApi(this)
            Log.d("Вызов в API", allFilms.size.toString())
        } else {
            Log.d("Лист заполнен", allFilms.size.toString() + allFilmGenres.size.toString())
            getData(allFilms)
        }
    }

    override fun getData(list: List<Film>) {
        allFilms = list

        if (allFilmGenres.isEmpty()) {
            allFilmGenres = getListAllGenres()
        }

        getDataForAdapter()
        fView.showData(allFilms)
    }

    override fun catchError(message: String) {
        fView.showError(message)
    }

    override fun decryptionCode(code: Int) {
        when (code) {
            403 -> catchError("Access to resource is forbidden")
            404 -> catchError("Resource not found")
            500 -> catchError("Internal server error")
            502 -> catchError("Bad Gateway")
            301 -> catchError("Resource has been removed permanently")
            302 -> catchError("Resource moved, but has been found")
            else -> catchError("All cases have not been covered!!")
        }
    }


    override fun getDataForAdapter() {
        val header = listOf(Header("Жанры", "Фильмы"))
        allFilms = allFilms.sortedBy { it.localized_name }
        selectedFilm = selectedFilm.sortedBy { it.localized_name }

        mergeringList = if (selectedFilm.isEmpty()) {
            preparationData(header, allFilmGenres, allFilms)
        } else {
            preparationData(header, allFilmGenres, selectedFilm)
        }

        fView.updateAdapter(mergeringList)
    }

    override fun sendSelected(genres: String) {
       if (!state) {
            selectedFilm = allFilms.filter { it.genres.contains(genres) }
            for (g in allFilmGenres) {
                g.isSelected = g.name == genres
            }
        } else {
            selectedFilm = allFilms
            for (g in allFilmGenres) {
                g.isSelected = false
            }
        }

        fView.showData(selectedFilm)
        getDataForAdapter()
    }


    override fun setView(view: Contract.View) {
        fView = view
    }

    fun getListAllGenres(): List<Genres> {
        val listGenres: MutableList<Genres> = mutableListOf()

        for (element in allFilms) {
            for (i in element.genres) {
                listGenres.addAll(listOf(Genres(i, false)))
            }
        }
        allGenres = listGenres.distinctBy { it.name }
        return allGenres
    }

    fun preparationData(listHeader: List<Header>, list1: List<Genres>, list2: List<Film>): List<ListItem> {
        val header = listHeader.map { ListItem.HeaderModel(it.header) }
        val genres = list1.map { ListItem.GenresModel(it) }
        val footer = listHeader.map { ListItem.HeaderModel(it.footer) }
        val films = list2.map { ListItem.FilmModel(it) }

        return header + genres + footer + films
    }
}
