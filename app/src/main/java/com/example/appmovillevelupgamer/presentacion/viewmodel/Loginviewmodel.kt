package com.example.appmovillevelupgamer.presentacion.login

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class LoginViewModel : ViewModel() {

    var correo by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var errorMensaje by mutableStateOf<String?>(null)

    // Usuarios de prueba (correo/usuario y password)
    private val usuariosValidos = listOf(
        Triple("admin", "admin123", "Administrador"),
        Triple("hector", "hector123", "Héctor Peña"),
        Triple("nelson", "nelson123", "Nelson Sanchez")
    )

    /**
     * Retorna el NOMBRE del usuario si está correcto.
     * Si no existe → retorna null.
     */
    fun validarLogin(): String? {

        val usuario = usuariosValidos.find {
            it.first.equals(correo.trim(), ignoreCase = true) &&
                    it.second == contrasena.trim()
        }

        return if (usuario != null) {
            errorMensaje = null
            usuario.third   // ← ESTE ES EL NOMBRE REAL
        } else {
            errorMensaje = "Correo o contraseña incorrectos"
            null
        }
    }
}
