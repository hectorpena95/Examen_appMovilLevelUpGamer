package com.example.appmovillevelupgamer.datos.repositorio

import com.example.appmovillevelupgamer.datos.api.ClienteClimaRetrofit
import com.example.appmovillevelupgamer.dominio.modelo.ClimaResponse

interface ClimaRepositorio {
    suspend fun obtenerClima(): ClimaResponse
}

class ClimaRepositorioImpl : ClimaRepositorio {
    override suspend fun obtenerClima(): ClimaResponse {
        return ClienteClimaRetrofit.api.obtenerClima()
    }
}
