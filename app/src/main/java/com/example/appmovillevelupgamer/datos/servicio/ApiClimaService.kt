package com.example.appmovillevelupgamer.datos.api

import com.example.appmovillevelupgamer.dominio.modelo.ClimaResponse
import retrofit2.http.GET

interface ApiClimaService {

    @GET("forecast?latitude=-27.366&longitude=-70.332&current_weather=true")
    suspend fun obtenerClima(): ClimaResponse
}
