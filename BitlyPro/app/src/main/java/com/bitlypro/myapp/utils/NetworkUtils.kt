package com.bitlypro.myapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

object NetworkUtils {
    
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
    
    fun getConnectionType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return "Unknown"
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "Unknown"
        
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WiFi"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Mobile Data"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "Ethernet"
            else -> "Unknown"
        }
    }
    
    fun getWifiSignalStrength(context: Context): Int {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        return WifiManager.calculateSignalLevel(wifiInfo.rssi, 5)
    }
    
    fun getCellularSignalStrength(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_LTE -> "4G LTE"
            TelephonyManager.NETWORK_TYPE_HSDPA,
            TelephonyManager.NETWORK_TYPE_HSUPA,
            TelephonyManager.NETWORK_TYPE_HSPA -> "3G"
            TelephonyManager.NETWORK_TYPE_EDGE,
            TelephonyManager.NETWORK_TYPE_GPRS -> "2G"
            TelephonyManager.NETWORK_TYPE_NR -> "5G"
            else -> "Unknown"
        }
    }
    
    fun formatSpeed(speedMbps: Double): String {
        return when {
            speedMbps >= 1000 -> String.format("%.1f Gbps", speedMbps / 1000)
            speedMbps >= 1 -> String.format("%.1f Mbps", speedMbps)
            else -> String.format("%.0f Kbps", speedMbps * 1000)
        }
    }
    
    fun formatPing(pingMs: Long): String {
        return "${pingMs}ms"
    }
    
    fun getSpeedQualityDescription(speedMbps: Double): String {
        return when {
            speedMbps >= 100 -> "Excellent"
            speedMbps >= 50 -> "Very Good"
            speedMbps >= 25 -> "Good"
            speedMbps >= 10 -> "Fair"
            speedMbps >= 5 -> "Poor"
            else -> "Very Poor"
        }
    }
}
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}