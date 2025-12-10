package com.example.appmovillevelupgamer

import com.example.appmovillevelupgamer.dominio.modelo.Producto
import com.example.appmovillevelupgamer.presentacion.viewmodel.CarritoViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CarritoViewModelTest {

    private lateinit var viewModel: CarritoViewModel

    @Before
    fun setup() {
        viewModel = CarritoViewModel()
    }

    private fun crearProducto(
        id: Long = 1L,
        nombre: String = "Mouse Gamer",
        descripcion: String = "RGB",
        precio: Int = 15000,
        stock: Int = 10,
        urlImagen: String = "",
        categoria: String = "Accesorios"
    ) = Producto(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        stock = stock,
        urlImagen = urlImagen,
        categoria = categoria
    )

    // -------------------------------------------------
    // 🔹 TEST 1: AGREGAR PRODUCTO AL CARRITO
    // -------------------------------------------------
    @Test
    fun agregarProducto_agregaCorrectamente() {
        val producto = crearProducto()

        viewModel.agregarProducto(producto)

        assertEquals(1, viewModel.carrito.size)
        assertEquals(producto, viewModel.carrito[0])
    }

    // -------------------------------------------------
    // 🔹 TEST 2: ELIMINAR PRODUCTO
    // -------------------------------------------------
    @Test
    fun eliminarProducto_eliminaCorrectamente() {
        val producto = crearProducto()

        viewModel.agregarProducto(producto)
        viewModel.eliminarProducto(producto)

        assertTrue(viewModel.carrito.isEmpty())
    }

    // -------------------------------------------------
    // 🔹 TEST 3: LIMPIAR CARRITO
    // -------------------------------------------------
    @Test
    fun limpiarCarrito_vaciaLista() {
        val p1 = crearProducto(id = 1)
        val p2 = crearProducto(id = 2)

        viewModel.agregarProducto(p1)
        viewModel.agregarProducto(p2)

        viewModel.limpiarCarrito()

        assertTrue(viewModel.carrito.isEmpty())
    }

    // -------------------------------------------------
    // 🔹 TEST 4: TOTAL DEL CARRITO
    // -------------------------------------------------
    @Test
    fun totalCalculo_esCorrecto() {
        val p1 = crearProducto(precio = 10000)
        val p2 = crearProducto(precio = 5000)

        viewModel.agregarProducto(p1)
        viewModel.agregarProducto(p2)

        assertEquals(15000.0, viewModel.total, 0.0)
    }
}
