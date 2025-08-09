package com.bitlypro.myapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bitlypro.myapp.databinding.ActivityMainBinding
import com.bitlypro.myapp.ui.speedtest.SpeedTestViewModel
import com.bitlypro.myapp.utils.ThemePreference
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val speedTestViewModel: SpeedTestViewModel by viewModels()
    private lateinit var themePreference: ThemePreference
    
    override fun onCreate(savedInstanceState: Bundle?) {
        themePreference = ThemePreference(this)
        applyTheme()
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupNavigation()
        setupThemeToggle()
    }
    
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
    
    private fun setupThemeToggle() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_theme_toggle -> {
                    lifecycleScope.launch {
                        themePreference.toggleTheme()
                        applyTheme()
                    }
                    true
                }
                else -> false
            }
        }
    }
    
    private fun applyTheme() {
        lifecycleScope.launch {
            val isDarkMode = themePreference.isDarkMode()
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }
}