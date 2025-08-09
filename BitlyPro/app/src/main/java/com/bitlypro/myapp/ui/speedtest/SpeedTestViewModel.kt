package com.bitlypro.myapp.ui.speedtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitlypro.myapp.data.repository.SpeedTestRepository
import kotlinx.coroutines.launch

class SpeedTestViewModel(private val repository: SpeedTestRepository) : ViewModel() {

    private val _downloadSpeed = MutableLiveData<Float>()
    val downloadSpeed: LiveData<Float> get() = _downloadSpeed

    private val _uploadSpeed = MutableLiveData<Float>()
    val uploadSpeed: LiveData<Float> get() = _uploadSpeed

    fun startSpeedTest() {
        viewModelScope.launch {
            // Logic to perform speed test and update LiveData
            val speedResults = repository.performSpeedTest()
            _downloadSpeed.postValue(speedResults.downloadSpeed)
            _uploadSpeed.postValue(speedResults.uploadSpeed)
            // Save results to database
            repository.saveSpeedTestResults(speedResults)
        }
    }
}