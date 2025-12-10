package com.example.appmovillevelupgamer.presentacion.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginPantalla(
    onLoginExitoso: ((String) -> Unit)? = null,
    onCrearCuenta: (() -> Unit)? = null,
    viewModel: LoginViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = viewModel.correo,
            onValueChange = { viewModel.correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.contrasena,
            onValueChange = { viewModel.contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🔥 Aquí está la validación correcta
        Button(
            onClick = {
                val nombre = viewModel.validarLogin()

                if (nombre != null) {
                    onLoginExitoso?.invoke(nombre)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = { onCrearCuenta?.invoke() }) {
            Text("¿No tienes cuenta? Crear una aquí")
        }

        viewModel.errorMensaje?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
