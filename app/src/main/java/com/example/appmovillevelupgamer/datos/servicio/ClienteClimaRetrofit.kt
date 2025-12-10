package com.example.appmovillevelupgamer.datos.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteClimaRetrofit {

    private const val BASE_URL = "https://api.open-meteo.com/v1/"

    private val cliente = OkHttpClient.Builder()
        .build()

    val api: ApiClimaService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClimaService::class.java)
    }
}
