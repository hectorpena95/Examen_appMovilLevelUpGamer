package com.example.appmovillevelupgamer.datos.repositorio

import com.example.appmovillevelupgamer.datos.api.ClienteClimaRetrofit
import com.example.appmovillevelupgamer.dominio.modelo.ClimaResponse

class ClimaRepositorio {

    suspend fun obtenerClima(): ClimaResponse {
        return ClienteClimaRetrofit.api.obtenerClima()
    }
}
