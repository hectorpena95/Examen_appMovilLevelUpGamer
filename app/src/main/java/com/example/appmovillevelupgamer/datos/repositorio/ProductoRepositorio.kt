package com.example.appmovillevelupgamer.datos.repositorio

import com.example.appmovillevelupgamer.datos.servicio.ClienteRetrofit
import com.example.appmovillevelupgamer.dominio.modelo.Producto

class ProductoRepositorio {

    suspend fun obtenerProductos(): List<Producto> {
        return ClienteRetrofit.api.obtenerProductos()
    }

    suspend fun obtenerProducto(id: Long): Producto {
        return ClienteRetrofit.api.obtenerProducto(id)
    }
}
