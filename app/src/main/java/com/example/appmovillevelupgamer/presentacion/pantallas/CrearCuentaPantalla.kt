package com.example.appmovillevelupgamer.presentacion.login

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.core.net.toUri
import java.io.File

@Composable
fun CrearCuentaPantalla(
    navController: NavController,
    onCuentaCreada: () -> Unit,
    vm: CrearCuentaViewModel = viewModel()
) {

    val context = LocalContext.current

    if (vm.cuentaCreada) {
        onCuentaCreada()
    }

    var mostrarContrasena by remember { mutableStateOf(false) }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var mostrarOpcionesFoto by remember { mutableStateOf(false) }

    // ----------------------- GALERÍA -----------------------
    val launcherGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            imagenUri = uri
            vm.fotoUri = uri
        }
    }

    // ----------------------- CÁMARA ------------------------
    val launcherCamara = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val uriFoto = guardarImagenTemporal(context, bitmap)
            imagenUri = uriFoto
            vm.fotoUri = uriFoto
        }
    }

    // -------- Solicitar permiso de cámara --------
    val launcherPermisoCamara = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            launcherCamara.launch(null)
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    // ----------------------- UI -----------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Crear cuenta nueva",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- BOTÓN AGREGAR FOTO DE PERFIL ----------
        Button(
            onClick = { mostrarOpcionesFoto = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar foto de perfil")
        }

        Spacer(modifier = Modifier.height(15.dp))

        // FOTO MOSTRADA REDONDA
        imagenUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Foto seleccionada",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        // ---------- DIÁLOGO CAMARA/GALERÍA (afuera del bloque de imagen) ----------
        if (mostrarOpcionesFoto) {
            AlertDialog(
                onDismissRequest = { mostrarOpcionesFoto = false },
                title = { Text("Seleccionar foto de perfil") },
                text = {
                    Column {

                        Button(
                            onClick = {
                                launcherGaleria.launch("image/*")
                                mostrarOpcionesFoto = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Elegir desde galería")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                launcherPermisoCamara.launch(android.Manifest.permission.CAMERA)
                                mostrarOpcionesFoto = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Tomar foto con cámara")
                        }
                    }
                },
                confirmButton = {}
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------- FORMULARIO -----------------------

        OutlinedTextField(
            value = vm.nombre,
            onValueChange = { vm.nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = vm.apellido,
            onValueChange = { vm.apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = vm.correo,
            onValueChange = { vm.correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = vm.contrasena,
            onValueChange = { vm.contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (mostrarContrasena) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (mostrarContrasena) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { mostrarContrasena = !mostrarContrasena }) {
                    Icon(imageVector = icon, contentDescription = "Mostrar/Ocultar contraseña")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = vm.edad,
            onValueChange = { vm.edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { vm.validarYCrearCuenta() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }

        vm.errorMensaje?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}

/**
 * Convierte un Bitmap en archivo temporal y devuelve su URI.
 */
fun guardarImagenTemporal(context: Context, bitmap: Bitmap): Uri {
    val archivo = File(context.cacheDir, "foto_${System.currentTimeMillis()}.jpg")
    archivo.outputStream().use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
    return archivo.toUri()
}
