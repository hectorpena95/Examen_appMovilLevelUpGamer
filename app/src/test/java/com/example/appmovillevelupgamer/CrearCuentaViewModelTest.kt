package com.example.appmovillevelupgamer

import com.example.appmovillevelupgamer.presentacion.login.CrearCuentaViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CrearCuentaViewModelTest {

    private lateinit var vm: CrearCuentaViewModel

    @Before
    fun setup() {
        vm = CrearCuentaViewModel()
    }

    @Test
    fun `campos vacios muestran error`() {
        vm.nombre = ""
        vm.apellido = ""
        vm.correo = ""
        vm.edad = ""

        vm.validarYCrearCuenta()

        assertEquals("Todos los campos son obligatorios", vm.errorMensaje)
        assertFalse(vm.cuentaCreada)
    }

    @Test
    fun `correo invalido`() {
        vm.nombre = "Hector"
        vm.apellido = "Pena"
        vm.correo = "correo_invalido"
        vm.edad = "20"

        vm.validarYCrearCuenta()

        assertEquals("El correo no es válido", vm.errorMensaje)
        assertFalse(vm.cuentaCreada)
    }

    @Test
    fun `edad menor a 10`() {
        vm.nombre = "Hector"
        vm.apellido = "Pena"
        vm.correo = "hector@test.com"
        vm.edad = "8"

        vm.validarYCrearCuenta()

        assertEquals("La edad debe ser un número mayor a 10", vm.errorMensaje)
        assertFalse(vm.cuentaCreada)
    }

    @Test
    fun `crear cuenta correctamente`() {
        vm.nombre = "Hector"
        vm.apellido = "Pena"
        vm.correo = "hector@test.com"
        vm.edad = "22"

        vm.validarYCrearCuenta()

        assertTrue(vm.cuentaCreada)
        assertNull(vm.errorMensaje)
    }
}
