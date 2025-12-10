package com.example.appmovillevelupgamer.presentacion.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovillevelupgamer.datos.repositorio.ProductoRepositorio
import com.example.appmovillevelupgamer.dominio.modelo.Producto
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val repo: ProductoRepositorio = ProductoRepositorio()
) : ViewModel() {

    // -------------------------------
    // LISTA DE PRODUCTOS
    // -------------------------------
    var productos by mutableStateOf<List<Producto>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    // -------------------------------
    // CARRITO DE COMPRAS
    // -------------------------------
    private val _carrito = mutableStateListOf<Producto>()
    val carrito: List<Producto> get() = _carrito

    // Total en tiempo real
    val totalCarrito: Int
        get() = _carrito.sumOf { it.precio }

    init {
        cargarProductos()
    }

    // -------------------------------
    // CARGA DE PRODUCTOS
    // -------------------------------
    fun cargarProductos() {
        viewModelScope.launch {
            try {
                cargando = true
                productos = repo.obtenerProductos()
            } catch (e: Exception) {
                error = e.message
            } finally {
                cargando = false
            }
        }
    }

    // -------------------------------
    // FUNCIONES DEL CARRITO
    // -------------------------------

    fun agregarAlCarrito(producto: Producto) {
        _carrito.add(producto)
    }

    fun eliminarDelCarrito(producto: Producto) {
        _carrito.remove(producto)
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }
}
