package com.example.appmovillevelupgamer.dominio.modelo

data class Producto(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val stock: Int,
    val urlImagen: String,
    val categoria: String
)
