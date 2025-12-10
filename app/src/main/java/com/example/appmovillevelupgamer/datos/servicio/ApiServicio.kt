package com.example.appmovillevelupgamer.datos.servicio

import com.example.appmovillevelupgamer.dominio.modelo.Producto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServicio {

    @GET("productos")
    suspend fun obtenerProductos(): List<Producto>

    @GET("productos/{id}")
    suspend fun obtenerProducto(@Path("id") id: Long): Producto

}
