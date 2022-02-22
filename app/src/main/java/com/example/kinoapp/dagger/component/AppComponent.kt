package com.example.kinoapp.dagger.component

import com.example.kinoapp.dagger.module.AppModule
import com.example.kinoapp.dagger.module.NetworkModule
import com.example.kinoapp.model.api.RetrofitService
import com.example.kinoapp.presenter.FilmPresenter
import com.example.kinoapp.view.FilmFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class])
interface AppComponent {
    fun inject(fragment: FilmFragment)
    fun getPresenter():FilmPresenter
    fun getRetrofit():RetrofitService
}