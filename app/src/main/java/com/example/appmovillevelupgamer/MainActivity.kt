package com.example.appmovillevelupgamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appmovillevelupgamer.presentacion.AppRoot
import com.example.appmovillevelupgamer.presentacion.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            // 🔥 ESTE ES EL *ÚNICO* UsuarioViewModel de toda la app
            val usuarioVM: UsuarioViewModel = viewModel()

            Surface(color = MaterialTheme.colorScheme.background) {
                AppRoot(navController, usuarioVM)
            }
        }
    }
}
