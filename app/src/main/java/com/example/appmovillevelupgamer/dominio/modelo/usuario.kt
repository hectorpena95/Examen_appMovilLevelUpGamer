package com.example.appmovillevelupgamer.dominio.modelo

import android.net.Uri

data class Usuario(
    val nombre: String = "",
    val apellido: String = "",
    val correo: String = "",
    val edad: Int = 0,
    val fotoPerfil: Uri? = null
)
