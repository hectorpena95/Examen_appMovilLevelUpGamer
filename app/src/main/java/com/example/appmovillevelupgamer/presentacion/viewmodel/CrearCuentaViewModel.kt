package com.example.appmovillevelupgamer.presentacion.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CrearCuentaViewModel : ViewModel() {

    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var correo by mutableStateOf("")
    var edad by mutableStateOf("")
    var errorMensaje by mutableStateOf<String?>(null)
    var cuentaCreada by mutableStateOf(false)
    var contrasena by mutableStateOf("")

    // FOTO (Galería + Cámara)
    var fotoUri by mutableStateOf<Uri?>(null)

    fun validarYCrearCuenta() {

        errorMensaje = null

        if (nombre.isBlank() || apellido.isBlank() || correo.isBlank() || edad.isBlank()) {
            errorMensaje = "Todos los campos son obligatorios"
            return
        }

        // REEMPLAZADO → funciona en Android Y en tests
        if (!esCorreoValido(correo)) {
            errorMensaje = "El correo no es válido"
            return
        }

        val edadNum = edad.toIntOrNull()
        if (edadNum == null || edadNum < 10) {
            errorMensaje = "La edad debe ser un número mayor a 10"
            return
        }

        cuentaCreada = true
    }

    // ✔ Regex universal (NO depende de Android Framework)
    private fun esCorreoValido(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return regex.matches(email)
    }
}
