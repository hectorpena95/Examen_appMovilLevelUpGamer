package com.example.appmovillevelupgamer.presentacion.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appmovillevelupgamer.dominio.modelo.Producto
import com.example.appmovillevelupgamer.presentacion.viewmodel.ProductoViewModel

@Composable
fun ListaProductosPantalla(
    navController: NavHostController,
    vm: ProductoViewModel
) {
    val fondoGamer: Brush = Brush.verticalGradient(
        listOf(
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

        // 🔙 Botón Volver fijo arriba a la izquierda
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {

        }

        // 📜 Contenido del catálogo, empezando debajo del botón
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)              // → en vez de Center
                .padding(start = 16.dp, end = 16.dp, top = 80.dp, bottom = 16.dp)
        ) {

            Text(
                text = "Catálogo de Productos",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))

            if (vm.cargando) {
                CircularProgressIndicator(color = Color.Cyan)
            }

            vm.error?.let {
                Text("Error: $it", color = Color.Yellow)
            }

            LazyColumn {
                items(vm.productos) { producto ->
                    ProductoItemGamer(producto) {
                        navController.navigate("detalle/${producto.id}")
                    }
                }
            }
        }
    }
}


@Composable
fun ProductoItemGamer(
    producto: Producto,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },   // ← AQUÍ!!
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = producto.urlImagen,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(producto.nombre, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text(producto.descripcion, color = Color(0xFFE0E0E0))
            Text("Precio: $${producto.precio}", color = Color.Cyan, style = MaterialTheme.typography.titleMedium)
        }
    }
}

