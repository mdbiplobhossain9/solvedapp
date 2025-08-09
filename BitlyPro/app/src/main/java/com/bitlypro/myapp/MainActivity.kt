package com.bitlypro.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bitlypro.myapp.ui.speedtest.SpeedTestFragment
import com.bitlypro.myapp.ui.charts.ChartFragment
import com.bitlypro.myapp.ui.theme.BitlyProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitlyProTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "speedTest") {
                    composable("speedTest") { SpeedTestFragment() }
                    composable("chart") { ChartFragment() }
                }
            }
        }
    }
}