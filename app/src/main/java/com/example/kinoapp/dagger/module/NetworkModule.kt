package com.example.kinoapp.dagger.module

import com.example.kinoapp.interfaces.Constant.Companion.BASE_URL
import com.example.kinoapp.model.api.API
import com.example.kinoapp.model.api.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): RetrofitService {

       val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}