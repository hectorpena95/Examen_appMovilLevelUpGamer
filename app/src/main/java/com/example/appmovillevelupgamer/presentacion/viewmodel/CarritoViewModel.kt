package com.example.appmovillevelupgamer.presentacion.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.appmovillevelupgamer.dominio.modelo.Producto

class CarritoViewModel : ViewModel() {

    private val _carrito = mutableStateListOf<Producto>()
    val carrito: List<Producto> get() = _carrito

    val total: Double
        get() = _carrito.sumOf { it.precio.toDouble() }

    fun agregarProducto(p: Producto) {
        _carrito.add(p)
    }

    fun eliminarProducto(p: Producto) {
        _carrito.remove(p)
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }
}
