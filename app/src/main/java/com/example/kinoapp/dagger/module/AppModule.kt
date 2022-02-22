package com.example.kinoapp.dagger.module

import com.example.kinoapp.presenter.FilmPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providePresenter() = FilmPresenter()
}