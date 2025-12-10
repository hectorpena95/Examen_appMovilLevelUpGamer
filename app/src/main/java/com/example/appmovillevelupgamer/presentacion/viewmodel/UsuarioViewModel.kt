package com.example.appmovillevelupgamer.presentacion.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {

    var nombreUsuario = mutableStateOf<String?>(null)

    fun setUsuario(nombre: String) {
        nombreUsuario.value = nombre
    }

    fun limpiar() {
        nombreUsuario.value = null
    }
}
