package com.example.appmovillevelupgamer.presentacion.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovillevelupgamer.datos.repositorio.ClimaRepositorio
import com.example.appmovillevelupgamer.dominio.modelo.ClimaActual
import kotlinx.coroutines.launch

class ClimaViewModel(
    private val repo: ClimaRepositorio = ClimaRepositorio()
) : ViewModel() {

    var clima by mutableStateOf<ClimaActual?>(null)
    var cargando by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun cargarClima() {
        viewModelScope.launch {
            try {
                cargando = true
                error = null
                clima = repo.obtenerClima().current_weather
            } catch (e: Exception) {
                error = e.message
            } finally {
                cargando = false
            }
        }
    }
}
