package com.example.appmovillevelupgamer.presentacion.pantallas

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmovillevelupgamer.R
import com.example.appmovillevelupgamer.presentacion.viewmodel.ClimaViewModel

@Composable
fun PantallaInicio(
    onExplorarCatalogo: () -> Unit,
    onIniciarSesion: () -> Unit,
    onIrPerfil: () -> Unit = {},
    nombreUsuario: String? = null
) {
    val climaVM: ClimaViewModel = viewModel()

    LaunchedEffect(true) {
        climaVM.cargarClima()
    }

    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )

    val fondoGamer = Brush.verticalGradient(
        listOf(
            Color(0xFF4500A0),
            Color(0xFF8B00F6),
            Color(0xFF0D0D0D)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGamer),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            // Logo animado
            Image(
                painter = painterResource(id = R.drawable.logo_levelup),
                contentDescription = "Logo LevelUpGamer",
                modifier = Modifier
                    .size(160.dp)
                    .scale(scale)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ⭐ SOLO SALUDAR SI REALMENTE HAY USUARIO ⭐
            if (!nombreUsuario.isNullOrBlank()) {

                Text(
                    text = "¡Bienvenido, $nombreUsuario! 👾",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = onExplorarCatalogo,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00E5FF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Explorar Catálogo", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = onIrPerfil,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD500),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Mi Perfil", fontSize = 18.sp)
                }
            } else {

                Button(
                    onClick = onExplorarCatalogo,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00E5FF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Explorar Catálogo", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onIniciarSesion,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4DFF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Iniciar Sesión", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                "Clima Actual",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            when {
                climaVM.cargando -> Text("Cargando clima...", color = Color.White)
                climaVM.error != null -> Text("Error: ${climaVM.error}", color = Color.Red)
                climaVM.clima != null -> {
                    Text("Temperatura: ${climaVM.clima!!.temperature}°C", color = Color.White)
                    Text("Viento: ${climaVM.clima!!.windspeed} km/h", color = Color.White)
                }
            }
        }
    }
}
