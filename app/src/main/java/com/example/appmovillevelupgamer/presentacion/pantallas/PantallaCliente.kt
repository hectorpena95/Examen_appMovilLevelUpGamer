package com.example.appmovillevelupgamer.presentacion.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaCliente(
    nombre: String,
    onCerrarSesion: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Bienvenido, $nombre 👤",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { onCerrarSesion() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}
