package com.example.appmovillevelupgamer.dominio.modelo

data class ClimaResponse(
    val current_weather: ClimaActual?
)

data class ClimaActual(
    val temperature: Double?,
    val windspeed: Double?,
    val weathercode: Int?
)
