package com.example.appmovillevelupgamer.datos.servicio

import android.os.Build

object BaseUrlProvider {

    // IP del servidor del instituto
    private const val IP_INSTITUTO = "10.31.83.231"

    // Puerto del backend
    private const val PUERTO = "8080"

    fun getBaseUrl(): String {

        val fingerprint = Build.FINGERPRINT.lowercase()

        val esEmulador = fingerprint.contains("generic") ||
                fingerprint.contains("emulator") ||
                fingerprint.contains("sdk_gphone")

        return if (esEmulador) {
            // ⭐ Emulador Android
            "http://10.0.2.2:$PUERTO/api/v1/"
        } else {
            // ⭐ Teléfono real conectado a la red del instituto
            "http://$IP_INSTITUTO:$PUERTO/api/v1/"
        }
    }
}
