package com.example.appmovillevelupgamer.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.appmovillevelupgamer.presentacion.login.LoginPantalla
import com.example.appmovillevelupgamer.presentacion.login.CrearCuentaPantalla
import com.example.appmovillevelupgamer.presentacion.cliente.PantallaCliente
import com.example.appmovillevelupgamer.presentacion.pantallas.PantallaInicio
import com.example.appmovillevelupgamer.presentacion.pantallas.ListaProductosPantalla
import com.example.appmovillevelupgamer.presentacion.pantallas.PantallaDetalleProducto
import com.example.appmovillevelupgamer.presentacion.pantallas.CarritoPantalla
import com.example.appmovillevelupgamer.presentacion.viewmodel.ProductoViewModel
import com.example.appmovillevelupgamer.presentacion.viewmodel.CarritoViewModel
import com.example.appmovillevelupgamer.presentacion.viewmodel.UsuarioViewModel

@Composable
fun Navegacion(
    navController: NavHostController,
    usuarioVM: UsuarioViewModel,
    modifier: Modifier = Modifier
)
 {

    val productosVM = ProductoViewModel()
    val carritoVM = CarritoViewModel()

    NavHost(
        navController = navController,
        startDestination = "inicio",
        modifier = modifier
    ) {

        // 🔹 INICIO
        composable("inicio") {
            PantallaInicio(
                onExplorarCatalogo = { navController.navigate("productos") },
                onIniciarSesion = { navController.navigate("login") },
                nombreUsuario = usuarioVM.nombreUsuario.value,
                onIrPerfil = { navController.navigate("cliente") }
            )
        }

        // 🔹 LOGIN
        composable("login") {
            LoginPantalla(
                onLoginExitoso = { nombre ->
                    usuarioVM.setUsuario(nombre)
                    navController.navigate("inicio") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onCrearCuenta = { navController.navigate("crearCuenta") }
            )
        }

        // 🔹 CREAR CUENTA
        composable("crearCuenta") {
            CrearCuentaPantalla(
                navController = navController,
                onCuentaCreada = {
                    navController.navigate("login") {
                        popUpTo("crearCuenta") { inclusive = true }
                    }
                }
            )
        }

        // 🔹 PERFIL CLIENTE
        composable("cliente") {
            PantallaCliente(
                nombre = usuarioVM.nombreUsuario.value ?: "Usuario",
                onCerrarSesion = {
                    usuarioVM.limpiar()
                    navController.navigate("inicio") {
                        popUpTo("cliente") { inclusive = true }
                    }
                }
            )
        }

        // 🔹 PRODUCTOS
        composable("productos") {
            ListaProductosPantalla(
                navController = navController,
                vm = productosVM
            )
        }

        // 🔹 DETALLE
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: -1L

            PantallaDetalleProducto(
                navController = navController,
                productoId = id,
                productosVM = productosVM,
                carritoVM = carritoVM
            )
        }

        // 🔹 CARRITO
        composable("carrito") {
            CarritoPantalla(
                navController = navController,
                vm = carritoVM
            )
        }
    }
}
