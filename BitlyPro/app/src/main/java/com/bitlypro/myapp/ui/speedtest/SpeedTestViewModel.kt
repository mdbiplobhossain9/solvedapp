package com.bitlypro.myapp.ui.speedtest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bitlypro.myapp.data.database.AppDatabase
import com.bitlypro.myapp.data.database.SpeedTestEntity
import com.bitlypro.myapp.data.network.SpeedTestService
import com.bitlypro.myapp.data.repository.SpeedTestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpeedTestViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = SpeedTestRepository(database.speedTestDao(), SpeedTestService())
    
    data class SpeedTestState(
        val isRunning: Boolean = false,
        val downloadSpeed: Double = 0.0,
        val uploadSpeed: Double = 0.0,
        val ping: Long = 0L,
        val progress: Int = 0,
        val currentTest: String = "",
        val error: String? = null
    )
    
    private val _speedTestState = MutableStateFlow(SpeedTestState())
    val speedTestState: StateFlow<SpeedTestState> = _speedTestState.asStateFlow()
    
    private val _testHistory = MutableLiveData<List<SpeedTestEntity>>()
    val testHistory: LiveData<List<SpeedTestEntity>> = _testHistory
    
    init {
        loadTestHistory()
    }
    
    fun startSpeedTest() {
        if (_speedTestState.value.isRunning) return
        
        viewModelScope.launch {
            try {
                _speedTestState.value = _speedTestState.value.copy(
                    isRunning = true,
                    error = null,
                    progress = 0,
                    currentTest = "Preparing test..."
                )
                
                // Reset values
                _speedTestState.value = _speedTestState.value.copy(
                    downloadSpeed = 0.0,
                    uploadSpeed = 0.0,
                    ping = 0L
                )
                
                // Test ping
                _speedTestState.value = _speedTestState.value.copy(
                    currentTest = "Testing ping...",
                    progress = 10
                )
                
                // Test download speed
                _speedTestState.value = _speedTestState.value.copy(
                    currentTest = "Testing download speed...",
                    progress = 40
                )
                
                // Test upload speed  
                _speedTestState.value = _speedTestState.value.copy(
                    currentTest = "Testing upload speed...",
                    progress = 70
                )
                
                // Run actual test
                val result = repository.runSpeedTest()
                
                _speedTestState.value = _speedTestState.value.copy(
                    isRunning = false,
                    downloadSpeed = result.downloadSpeed,
                    uploadSpeed = result.uploadSpeed,
                    ping = result.ping,
                    progress = 100,
                    currentTest = "Test completed!"
                )
                
                loadTestHistory()
                
            } catch (e: Exception) {
                _speedTestState.value = _speedTestState.value.copy(
                    isRunning = false,
                    error = e.message ?: "An error occurred during the speed test",
                    progress = 0,
                    currentTest = "Test failed"
                )
            }
        }
    }
    
    fun stopSpeedTest() {
        _speedTestState.value = _speedTestState.value.copy(
            isRunning = false,
            currentTest = "Test stopped",
            progress = 0
        )
    }
    
    private fun loadTestHistory() {
        viewModelScope.launch {
            repository.getRecentResults(10).collect { results ->
                _testHistory.value = results
            }
        }
    }
    
    fun deleteResult(result: SpeedTestEntity) {
        viewModelScope.launch {
            repository.deleteResult(result)
            loadTestHistory()
        }
    }
    
    fun clearAllResults() {
        viewModelScope.launch {
            repository.deleteAllResults()
            loadTestHistory()
        }
    }
}