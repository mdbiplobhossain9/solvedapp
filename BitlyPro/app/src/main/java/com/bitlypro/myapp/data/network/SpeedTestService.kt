package com.bitlypro.myapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class SpeedTestService {
    
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    data class SpeedTestResult(
        val downloadSpeed: Double, // Mbps
        val uploadSpeed: Double,   // Mbps
        val ping: Long            // ms
    )
    
    suspend fun runSpeedTest(): SpeedTestResult = withContext(Dispatchers.IO) {
        val ping = measurePing()
        val downloadSpeed = measureDownloadSpeed()
        val uploadSpeed = measureUploadSpeed()
        
        SpeedTestResult(downloadSpeed, uploadSpeed, ping)
    }
    
    private suspend fun measurePing(): Long = withContext(Dispatchers.IO) {
        try {
            val startTime = System.currentTimeMillis()
            InetAddress.getByName("8.8.8.8").isReachable(5000)
            val endTime = System.currentTimeMillis()
            endTime - startTime
        } catch (e: Exception) {
            -1L
        }
    }
    
    private suspend fun measureDownloadSpeed(): Double = withContext(Dispatchers.IO) {
        try {
            // Using a test file from a speed test server
            val url = "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
            val request = Request.Builder().url(url).build()
            
            val startTime = System.currentTimeMillis()
            val response = client.newCall(request).execute()
            val bytes = response.body?.bytes()?.size ?: 0
            val endTime = System.currentTimeMillis()
            
            val durationSeconds = (endTime - startTime) / 1000.0
            val megabits = (bytes * 8) / 1_000_000.0
            
            if (durationSeconds > 0) megabits / durationSeconds else 0.0
        } catch (e: Exception) {
            0.0
        }
    }
    
    private suspend fun measureUploadSpeed(): Double = withContext(Dispatchers.IO) {
        try {
            // Create test data (1MB)
            val testData = ByteArray(1024 * 1024) { 0 }
            val requestBody = testData.toRequestBody("application/octet-stream".toMediaType())
            
            val request = Request.Builder()
                .url("https://httpbin.org/post")
                .post(requestBody)
                .build()
            
            val startTime = System.currentTimeMillis()
            val response = client.newCall(request).execute()
            val endTime = System.currentTimeMillis()
            
            val durationSeconds = (endTime - startTime) / 1000.0
            val megabits = (testData.size * 8) / 1_000_000.0
            
            if (durationSeconds > 0 && response.isSuccessful) megabits / durationSeconds else 0.0
        } catch (e: Exception) {
            0.0
        }
    }
}