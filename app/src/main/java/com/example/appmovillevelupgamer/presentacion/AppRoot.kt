package com.example.appmovillevelupgamer.presentacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appmovillevelupgamer.navegacion.Navegacion
import com.example.appmovillevelupgamer.presentacion.viewmodel.UsuarioViewModel

@Composable
fun AppRoot(
    navController: NavHostController,
    usuarioVM: UsuarioViewModel
) {

    val rutasSinBarra = listOf("login", "crearCuenta")

    val items = listOf(
        BottomItem("inicio", Icons.Default.Home),
        BottomItem("productos", Icons.Default.List),
        BottomItem("carrito", Icons.Default.ShoppingCart),
        BottomItem("cliente", Icons.Default.Person)
    )

    val selectedColor = Color(0xFF00E5FF)
    val unselectedColor = Color(0xFF777777)
    val barColor = Color(0xFF141414)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mostrarBottomBar = currentRoute !in rutasSinBarra

    Scaffold(
        bottomBar = {
            if (mostrarBottomBar) {
                NavigationBar(containerColor = barColor) {
                    items.forEach { item ->

                        val selected = currentRoute == item.route

                        NavigationBarItem(
                            selected = selected,
                            onClick = {

                                if (item.route == "cliente") {

                                    if (usuarioVM.nombreUsuario.value.isNullOrBlank()) {
                                        usuarioVM.limpiar()
                                        navController.navigate("login")
                                        return@NavigationBarItem
                                    }

                                    navController.navigate("cliente")
                                    return@NavigationBarItem
                                }

                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo("inicio") { inclusive = false }
                                }
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = null,
                                    tint = if (selected) selectedColor else unselectedColor
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->

        Navegacion(
            navController = navController,
            usuarioVM = usuarioVM,
            modifier = Modifier.padding(padding)
        )
    }
}

data class BottomItem(
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
