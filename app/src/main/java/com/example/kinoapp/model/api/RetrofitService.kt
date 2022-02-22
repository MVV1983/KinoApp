package com.example.kinoapp.model.api

import com.example.kinoapp.model.datamodel.Films
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/sequeniatesttask/films.json")
    fun getFilms(): Call<Films>
}