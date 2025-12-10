package com.example.appmovillevelupgamer.presentacion.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appmovillevelupgamer.presentacion.viewmodel.CarritoViewModel
import com.example.appmovillevelupgamer.presentacion.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@Composable
fun PantallaDetalleProducto(
    navController: NavHostController,
    productoId: Long,
    productosVM: ProductoViewModel,
    carritoVM: CarritoViewModel
) {
    val producto = productosVM.productos.find { it.id == productoId }

    val fondoGamer = Brush.verticalGradient(
        listOf(
            Color(0xFF4500A0),
            Color(0xFF8B00F6),
            Color(0xFF0D0D0D)
        )
    )

    // 👉 Controlador del snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(fondoGamer)
        ) {

            if (producto == null) {
                Text("Producto no encontrado", color = Color.White)
                return@Box
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {


                Spacer(Modifier.height(16.dp))

                AsyncImage(
                    model = producto.urlImagen,
                    contentDescription = producto.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(producto.nombre, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Spacer(Modifier.height(8.dp))
                Text(producto.descripcion, color = Color(0xFFE0E0E0))
                Spacer(Modifier.height(16.dp))

                Text("Precio: $${producto.precio}", color = Color.Cyan, style = MaterialTheme.typography.headlineLarge)
                Spacer(Modifier.height(30.dp))

                // 🟢 AGREGAR AL CARRITO + SNACKBAR
                Button(
                    onClick = {
                        carritoVM.agregarProducto(producto)

                        // 👉 Mostrar snackbar
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Producto agregado al carrito",
                                withDismissAction = true
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00E5FF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Agregar al Carrito")
                }

                Spacer(Modifier.height(20.dp))

                // 🟣 COMPRAR AHORA
                Button(
                    onClick = {
                        carritoVM.agregarProducto(producto)
                        navController.navigate("carrito")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4DFF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Comprar Ahora")
                }
            }
        }
    }
}

