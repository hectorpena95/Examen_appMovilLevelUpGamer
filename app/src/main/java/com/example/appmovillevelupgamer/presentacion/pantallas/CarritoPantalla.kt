package com.example.appmovillevelupgamer.presentacion.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appmovillevelupgamer.presentacion.viewmodel.CarritoViewModel

@Composable
fun CarritoPantalla(
    navController: NavHostController,
    vm: CarritoViewModel = viewModel()
) {
    val fondoGamer = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF4500A0),
            Color(0xFF8B00F6),
            Color(0xFF0D0D0D)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGamer)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Carrito de Compras",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(vm.carrito) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1A1A1A)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // MINIATURA
                            AsyncImage(
                                model = producto.urlImagen,
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            Column(Modifier.weight(1f)) {

                                Text(
                                    producto.nombre,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Text(
                                    "$${producto.precio}",
                                    color = Color.Cyan
                                )

                                Spacer(Modifier.height(8.dp))

                                Button(
                                    onClick = { vm.eliminarProducto(producto) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }

            // Total + Comprar
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Total: $${vm.total}",
                    color = Color.Yellow,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        vm.limpiarCarrito()
                        navController.navigate("productos")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00E5FF),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("Comprar")
                }
            }
        }
    }
}
