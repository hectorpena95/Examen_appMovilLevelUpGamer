package com.example.appmovillevelupgamer.datos.servicio

import android.os.Build
import java.net.NetworkInterface
import java.net.Inet4Address

object BaseUrlProvider {

    private const val IP_INSTITUTO = "10.31.83.231"
    private const val IP_CASA = "192.168.1.87"
    private const val PUERTO = "8080"

    fun getBaseUrl(): String {

        val fingerprint = Build.FINGERPRINT.lowercase()

        val esEmulador = fingerprint.contains("generic") ||
                fingerprint.contains("emulator") ||
                fingerprint.contains("sdk_gphone")

        return when {
            esEmulador -> {
                println("➡ Emulador detectado: usando 10.0.2.2")
                "http://10.0.2.2:$PUERTO/api/v1/"
            }

            estaEnRedCasa() -> {
                println("➡ Red de casa detectada: usando $IP_CASA")
                "http://$IP_CASA:$PUERTO/api/v1/"
            }

            else -> {
                println("➡ Red del instituto detectada: usando $IP_INSTITUTO")
                "http://$IP_INSTITUTO:$PUERTO/api/v1/"
            }
        }
    }

    // Detecta si la IP local está en rango 192.168.x.x
    private fun estaEnRedCasa(): Boolean {
        return try {
            val interfaces = NetworkInterface.getNetworkInterfaces()
            for (intf in interfaces) {
                for (addr in intf.inetAddresses) {
                    if (addr is Inet4Address) {
                        val ip = addr.hostAddress
                        if (ip.startsWith("192.168.")) {
                            return true
                        }
                    }
                }
            }
            false
        } catch (e: Exception) {
            false
        }
    }
}
