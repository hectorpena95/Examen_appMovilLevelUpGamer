package com.example.appmovillevelupgamer

import com.example.appmovillevelupgamer.datos.repositorio.ClimaRepositorio
import com.example.appmovillevelupgamer.dominio.modelo.ClimaActual
import com.example.appmovillevelupgamer.dominio.modelo.ClimaResponse
import com.example.appmovillevelupgamer.presentacion.viewmodel.ClimaViewModel
import junit.framework.TestCase.assertNotNull

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class ClimaViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    private lateinit var repo: ClimaRepositorio
    private lateinit var viewModel: ClimaViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = Mockito.mock(ClimaRepositorio::class.java)
        viewModel = ClimaViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cargar clima correctamente`() = runTest {
        val fakeClima = ClimaResponse(
            current_weather = ClimaActual(
                temperature = 22.5,
                windspeed = 10.0,
                weathercode = 1
            )
        )

        Mockito.`when`(repo.obtenerClima()).thenReturn(fakeClima)

        viewModel.cargarClima()
        dispatcher.scheduler.advanceUntilIdle()

        assertNotNull(viewModel.clima)
        assertEquals(22.5, viewModel.clima?.temperature)
    }

    @Test
    fun `manejar error al obtener clima`() = runTest {
        Mockito.`when`(repo.obtenerClima())
            .thenThrow(RuntimeException("Error de red"))

        viewModel.cargarClima()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("Error de red", viewModel.error)
    }
}
