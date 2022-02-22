package com.example.kinoapp

import android.app.Application

import com.example.kinoapp.dagger.component.AppComponent
import com.example.kinoapp.dagger.component.DaggerAppComponent

open class App : Application() {

companion object {
    lateinit var appComponent: AppComponent
}

    override fun onCreate() {
        super.onCreate()
        ComponentInitializer()
    }

    private fun ComponentInitializer() {
        appComponent =  DaggerAppComponent.builder().build()
    }
}