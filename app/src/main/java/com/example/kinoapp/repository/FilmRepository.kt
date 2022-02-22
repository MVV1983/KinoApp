package com.example.kinoapp.repository

import android.util.Log
import com.example.kinoapp.App
import com.example.kinoapp.interfaces.Contract
import com.example.kinoapp.model.api.API
import com.example.kinoapp.model.api.RetrofitService
import com.example.kinoapp.model.datamodel.*
import com.example.kinoapp.presenter.FilmPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FilmRepository : Contract.Model {

    private var fullListFromApi: MutableList<Film> = mutableListOf()

    @Inject
    lateinit var api: RetrofitService

    override fun requestApi(presenter: Contract.Presenter) {
        api= App.appComponent.getRetrofit()
            val call = api.getFilms()//API.create().getFilms()

            call.enqueue(object : Callback<Films> {
                override fun onResponse(call: Call<Films>, response: Response<Films>) {
                    fullListFromApi.clear()
                    response.body()?.let { fullListFromApi.addAll(it.films) }

                    response.body()?.films?.let { presenter.getData(it) }

                    //response.code()
                    presenter.decryptionCode(response.code())

                    Log.d("API LIST", fullListFromApi.size.toString())
                }

                override fun onFailure(call: Call<Films>, t: Throwable) {
                    Log.d("onFailure","onFailure")
                }
            })
    }
}